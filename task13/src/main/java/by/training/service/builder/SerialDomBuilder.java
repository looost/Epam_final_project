package by.training.service.builder;

import by.training.entity.*;
import by.training.service.exception.ServiceException;
import by.training.service.validation.ValidationXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class SerialDomBuilder extends BaseBuilder {

    private Logger logger = LogManager.getLogger("logger");
    private static final String schemaPath = "E:\\Java-Training\\task13\\src\\main\\resources\\xml\\serials.xsd";
    private DocumentBuilder docBuilder;

    public SerialDomBuilder() {
        this.serials = new HashSet<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Ошибка конфигурации парсера: " + e);
        }
    }

    @Override
    public void buildSetSerials(String fileName) throws ServiceException {

        logger.info("Работает DOM builder");

        if (!ValidationXML.xmlIsValid(fileName, schemaPath)) {
            logger.error("Invalid XML");
            throw new ServiceException("Invalid XML");
        }

        Document doc = null;
        try {
            // parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            // получение списка дочерних элементов <student>
            NodeList serialList = root.getElementsByTagName("serial");
            for (int i = 0; i < serialList.getLength(); i++) {
                Element serialElement = (Element) serialList.item(i);
                Serial serial = buildSerial(serialElement);
                serials.add(serial);
            }
        } catch (IOException e) {
            logger.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.error("Parsing failure: " + e);
        }
    }

    private Serial buildSerial(Element serialElement) {
        Serial serial = new Serial();
        // заполнение объекта student
        serial.setId(Integer.parseInt(serialElement.getAttribute("id")));
        serial.setName(getElementTextContent(serialElement, "name"));
        serial.setDescription(getElementTextContent(serialElement, "description"));
        serial.setLogo(getElementTextContent(serialElement, "logo"));
        serial.setFullLogo(getElementTextContent(serialElement, "fullLogo"));
        serial.setReleaseDate(Date.valueOf(getElementTextContent(serialElement, "releaseDate")));

        Country country = new Country();
        Element countryElement = (Element) serialElement.getElementsByTagName("country").item(0);
        country.setId(Integer.parseInt(countryElement.getAttribute("id")));
        country.setName(getElementTextContent(countryElement, "name"));
        serial.setCountry(country);

        Studio studio = new Studio();
        Element studioElement = (Element) serialElement.getElementsByTagName("studio").item(0);
        studio.setId(Integer.parseInt(countryElement.getAttribute("id")));
        studio.setName(getElementTextContent(studioElement, "name"));
        serial.setStudio(studio);

        NodeList genreList = serialElement.getElementsByTagName("genre");
        Set<Genre> genreSet = new HashSet<>();
        for (int i = 0; i < genreList.getLength(); i++) {
            Element genreElement = (Element) genreList.item(i);
            Genre genre = buildGenre(genreElement);
            genreSet.add(genre);
        }
        serial.setGenres(genreSet);

        NodeList commentList = serialElement.getElementsByTagName("comment");
        Set<Comment> commentSet = new HashSet<>();
        for (int i = 0; i < commentList.getLength(); i++) {
            Element commentElement = (Element) commentList.item(i);
            Comment comment = buildComment(commentElement);
            commentSet.add(comment);
        }
        serial.setComments(commentSet);
        return serial;
    }

    private Genre buildGenre(Element genreElement) {
        Genre genre = new Genre();
        genre.setId(Integer.parseInt(genreElement.getAttribute("id")));
        genre.setName(getElementTextContent(genreElement, "name"));
        return genre;
    }

    private Comment buildComment(Element commentElement) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentElement.getAttribute("id")));

        User user = new User();
        Element userElement = (Element) commentElement.getElementsByTagName("user").item(0);
        user.setId(Integer.parseInt(userElement.getAttribute("id")));
        user.setRole(Integer.parseInt(userElement.getAttribute("role")));
        user.setLogin(getElementTextContent(userElement, "login"));
        user.setPassword(getElementTextContent(userElement, "password"));

        comment.setUser(user);
        comment.setComment(getElementTextContent(commentElement, "commentText"));
        comment.setPublicationDate(Date.valueOf(getElementTextContent(commentElement, "publicationDate")));
        return comment;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
