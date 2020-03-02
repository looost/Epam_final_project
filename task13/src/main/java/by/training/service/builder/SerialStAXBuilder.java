package by.training.service.builder;

import by.training.entity.*;
import by.training.service.SerialEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class SerialStAXBuilder {
    private Set<Serial> serials = new HashSet<>();
    private XMLInputFactory inputFactory;

    public SerialStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    public void buildSetStudents(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.SERIAL) {
                        Serial serial = buildSerial(reader);
                        serials.add(serial);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Serial buildSerial(XMLStreamReader reader) throws XMLStreamException {
        Serial serial = new Serial();
        serial.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            serial.setName(getXMLText(reader));
                            break;
                        case DESCRIPTION:
                            serial.setDescription(getXMLText(reader));
                            break;
                        case LOGO:
                            serial.setLogo(getXMLText(reader));
                            break;
                        case FULLLOGO:
                            serial.setFullLogo(getXMLText(reader));
                            break;
                        case RELEASEDATE:
                            serial.setReleaseDate(Date.valueOf(getXMLText(reader)));
                            break;
                        case COUNTLIKE:
                            serial.setCountLike(Integer.parseInt(getXMLText(reader)));
                            break;
                        case COUNTRY:
                            serial.setCountry(getXMLCountry(reader));
                            break;
                        case STUDIO:
                            serial.setStudio(getXMLStudio(reader));
                            break;
                        case GENRES:
                            serial.setGenres(getXMLGenres(reader));
                            break;
                        case COMMENTS:
                            serial.setComments(getXMLComments(reader));
                            break;
                        default:
                            throw new XMLStreamException("Unknown element in tag Serial");
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.SERIAL) {
                        return serial;
                    }
                    break;
                default:
                    throw new XMLStreamException("Unknown element in tag Serial");
            }
        }
        throw new XMLStreamException("Unknown element in tag Serial");
    }

    private Country getXMLCountry(XMLStreamReader reader) throws XMLStreamException {
        Country country = new Country();
        country.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            country.setName(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.COUNTRY) {
                        return country;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Country");
    }

    private Studio getXMLStudio(XMLStreamReader reader) throws XMLStreamException {
        Studio studio = new Studio();
        studio.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            studio.setName(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.STUDIO) {
                        return studio;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Studio");
    }

    private Set<Genre> getXMLGenres(XMLStreamReader reader) throws XMLStreamException {
        Set<Genre> genreSet = new HashSet<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case GENRE:
                            genreSet.add(getXMLGenre(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.GENRES) {
                        return genreSet;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Genres");
    }

    private Genre getXMLGenre(XMLStreamReader reader) throws XMLStreamException {
        Genre genre = new Genre();
        genre.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            genre.setName(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.GENRE) {
                        return genre;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Genre");
    }

    private Set<Comment> getXMLComments(XMLStreamReader reader) throws XMLStreamException {
        Set<Comment> commentSet = new HashSet<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case COMMENT:
                            commentSet.add(getXMLComment(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.COMMENTS) {
                        return commentSet;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Comments");
    }

    private Comment getXMLComment(XMLStreamReader reader) throws XMLStreamException {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case USER:
                            comment.setUser(getXMLUser(reader));
                            break;
                        case COMMENTTEXT:
                            comment.setComment(getXMLText(reader));
                            break;
                        case PUBLICATIONDATE:
                            comment.setPublicationDate(Date.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.COMMENT) {
                        return comment;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Comment");
    }

    private User getXMLUser(XMLStreamReader reader) throws XMLStreamException {
        User user = new User();
        user.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (SerialEnum.valueOf(name.toUpperCase())) {
                        case LOGIN:
                            user.setLogin(getXMLText(reader));
                            break;
                        case PASSWORD:
                            user.setPassword(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (SerialEnum.valueOf(name.toUpperCase()) == SerialEnum.USER) {
                        return user;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag User");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public static void main(String[] args) {
        SerialStAXBuilder stAXBuilder = new SerialStAXBuilder();
        stAXBuilder.buildSetStudents("src\\main\\resources\\xml\\serials.xml");
        System.out.println(stAXBuilder.getSerials());
    }

}
