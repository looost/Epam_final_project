package by.training.service;

import by.training.entity.Country;
import by.training.entity.Genre;
import by.training.entity.Serial;
import by.training.entity.Studio;
import by.training.service.builder.GenreSAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class SerialHandler2 extends DefaultHandler {

    private Logger logger = LogManager.getLogger("logger");
    private Set<Serial> serials;
    private Serial current;
    private SerialEnum currentEnum = null;
    private EnumSet<SerialEnum> withText;
    private GenreSAXBuilder saxBuilder;

    public SerialHandler2() {
        this.serials = new HashSet<>();
        withText = EnumSet.range(SerialEnum.NAME, SerialEnum.COMMENTS);
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
            current.setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("country".equals(localName)) {
            current.getCountry().setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("studio".equals(localName)) {
            current.getStudio().setId(Integer.parseInt(attributes.getValue(0)));
        } else if ("comments".equals(localName)) {
            current.setComments(new HashSet<>());
        }
//        else if ("genres".equals(localName)) {
//                    saxBuilder = new GenreSAXBuilder();
//                    saxBuilder.buildGenres("src\\main\\resources\\xml\\serials.xml");
//                    current.setGenres(saxBuilder.getGenres());
//        }
        else {
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
                    current.setName(s);
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
                case COUNTRY:
                    current.getCountry().setName(s);
                    break;
                case STUDIO:
                    current.getStudio().setName(s);
                    break;
                case COMMENTS:
                    break;
                default:
                    logger.info("default case - " + currentEnum);
            }
        }
        saxBuilder = null;
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
