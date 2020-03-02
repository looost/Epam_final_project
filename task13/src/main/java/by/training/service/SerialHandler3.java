package by.training.service;

import by.training.entity.*;
import by.training.service.builder.GenreSAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLInputFactory;
import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class SerialHandler3 extends DefaultHandler {

    private Logger logger = LogManager.getLogger("logger");
    private Set<Serial> serials;
    private Serial current;

    private Genre currentGenre;

    private Comment currentComment;

    private String currentTeg;
    private SerialEnum currentEnum = null;
    private EnumSet<SerialEnum> withText;

    public SerialHandler3() {
        this.serials = new HashSet<>();
        withText = EnumSet.range(SerialEnum.NAME, SerialEnum.PUBLICATIONDATE);
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        logger.info(localName);
        if ("serial".equals(localName)) {
            current = new Serial();
            current.setCountry(new Country());
            current.setStudio(new Studio());
            current.setGenres(new HashSet<>());
            current.setComments(new HashSet<>());
            current.setId(Integer.parseInt(attributes.getValue(0)));
            currentTeg = localName;
        } else if ("country".equals(localName)) {
            currentTeg = localName;
            current.getCountry().setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("studio".equals(localName)) {
            currentTeg = "studio";
            current.getStudio().setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("genre".equals(localName)) {
            currentTeg = localName;
            currentGenre = new Genre();
            currentGenre.setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("comment".equals(localName)) {
            currentComment = new Comment();
            currentComment.setUser(new User());
            currentComment.setId(Integer.parseInt(attributes.getValue(0)));
        } else {
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
            if (currentEnum.equals(SerialEnum.NAME) && currentTeg.equals("serial")) {
                current.setName(s);
            } else if (currentEnum.equals(SerialEnum.DESCRIPTION)) {
                current.setDescription(s);
            } else if (currentEnum.equals(SerialEnum.LOGO)) {
                current.setLogo(s);
            } else if (currentEnum.equals(SerialEnum.FULLLOGO)) {
                current.setFullLogo(s);
            } else if (currentEnum.equals(SerialEnum.RELEASEDATE)) {
                current.setReleaseDate(Date.valueOf(s));
            } else if (currentEnum.equals(SerialEnum.COUNTLIKE)) {
                current.setCountLike(Integer.parseInt(s));
            } else if (currentEnum.equals(SerialEnum.NAME) && currentTeg.equals("country")) {
                current.getCountry().setName(s);
            } else if (currentEnum.equals(SerialEnum.NAME) && currentTeg.equals("studio")) {
                current.getStudio().setName(s);
            } else if (currentEnum.equals(SerialEnum.NAME) && currentTeg.equals("genre")) {
                currentGenre.setName(s);
                current.getGenres().add(currentGenre);
            } else if (currentEnum.equals(SerialEnum.LOGIN)) {
                currentComment.getUser().setLogin(s);
            } else if (currentEnum.equals(SerialEnum.PASSWORD)) {
                currentComment.getUser().setPassword(s);
            } else if (currentEnum.equals(SerialEnum.COMMENTTEXT)) {
                currentComment.setComment(s);
                current.getComments().add(currentComment);
            } else if (currentEnum.equals(SerialEnum.PUBLICATIONDATE)) {
                currentComment.setPublicationDate(Date.valueOf(s));
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

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nParsing ended");
    }
}
