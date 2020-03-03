package by.training.service;

import by.training.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class SerialHandler extends DefaultHandler {

    private Logger logger = LogManager.getLogger("logger");
    private Set<Serial> serials;
    private Serial current;

    private Genre currentGenre;

    private Comment currentComment;

    private String currentTeg;
    private SerialEnum currentEnum = null;
    private EnumSet<SerialEnum> withText;

    public SerialHandler() {
        this.serials = new HashSet<>();
        withText = EnumSet.range(SerialEnum.NAME, SerialEnum.PUBLICATIONDATE);
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //logger.info(localName);
        switch (localName) {
            case "serial":
                current = new Serial();
                current.setId(Integer.parseInt(attributes.getValue(0)));
                currentTeg = localName;
                break;
            case "country":
                currentTeg = localName;
                current.setCountry(new Country());
                current.getCountry().setId(Integer.parseInt(attributes.getValue(0)));
                break;
            case "studio":
                currentTeg = "studio";
                current.setStudio(new Studio());
                current.getStudio().setId(Integer.parseInt(attributes.getValue(0)));
                break;
            case "genres":
                current.setGenres(new HashSet<>());
                break;
            case "genre":
                currentTeg = localName;
                currentGenre = new Genre();
                currentGenre.setId(Integer.parseInt(attributes.getValue(0)));
                break;
            case "comments":
                current.setComments(new HashSet<>());
                break;
            case "comment":
                currentComment = new Comment();
                currentComment.setUser(new User());
                currentComment.setId(Integer.parseInt(attributes.getValue(0)));
                break;
            case "user":
                currentComment.getUser().setId(Integer.parseInt(attributes.getValue(0)));
                currentComment.getUser().setRole(Integer.parseInt(attributes.getValue(1)));
                break;
            default:
                SerialEnum temp = SerialEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    switch (currentTeg) {
                        case "serial":
                            current.setName(s);
                            break;
                        case "country":
                            current.getCountry().setName(s);
                            break;
                        case "studio":
                            current.getStudio().setName(s);
                            break;
                        case "genre":
                            currentGenre.setName(s);
                            current.getGenres().add(currentGenre);
                            break;
                        default:
                            logger.error("Unknown element in tag " + currentEnum + " currentTag - " + currentTeg);
                            throw new SAXException("Unknown element in tag " + currentEnum + " currentTag - " + currentTeg);
                    }
                    break;
                case DESCRIPTION:
                    current.setDescription(s);
                    break;
                case LOGO:
                    current.setLogo(s);
                    break;
                case FULLLOGO:
                    current.setFullLogo(s);
                    break;
                case RELEASEDATE:
                    current.setReleaseDate(Date.valueOf(s));
                    break;
                case COUNTLIKE:
                    current.setCountLike(Integer.parseInt(s));
                    break;
                case LOGIN:
                    currentComment.getUser().setLogin(s);
                    break;
                case PASSWORD:
                    currentComment.getUser().setPassword(s);
                    break;
                case COMMENTTEXT:
                    currentComment.setComment(s);
                    current.getComments().add(currentComment);
                    break;
                case PUBLICATIONDATE:
                    currentComment.setPublicationDate(Date.valueOf(s));
                    break;
                default:
                    logger.error("Unknown element in tag " + currentEnum);
                    throw new SAXException("Unknown element in tag " + currentEnum);

            }
            currentTeg = "";
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("serial".equals(localName)) {
            serials.add(current);
        }
    }
}
