package by.training.service.builder;

import by.training.entity.Serial;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SerialDomBuilder {
    private Set<Serial> serials;
    private DocumentBuilder docBuilder;

    public SerialDomBuilder() {
        this.serials = new HashSet<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    public void buildSetStudents(String fileName) {
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
                System.err.println(serial);
                serials.add(serial);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Serial buildSerial(Element serialElement) {
        Serial serial = new Serial();
        // заполнение объекта student
        serial.setId(Integer.parseInt(serialElement.getAttribute("id")));
        serial.setName(getElementTextContent(serialElement, "name"));
        serial.setDescription(getElementTextContent(serialElement, "description"));
        serial.setLogo(getElementTextContent(serialElement, "logo"));
        serial.setFullLogo(getElementTextContent(serialElement, "fulllogo"));
//        Student.Address address = student.getAddress();
//        // заполнение объекта address
//        Element adressElement = (Element) studentElement.getElementsByTagName(
//                "address").item(0);
//        address.setCountry(getElementTextContent(adressElement, "country"));
//        address.setCity(getElementTextContent(adressElement, "city"));
//        address.setStreet(getElementTextContent(adressElement, "street"));
//        student.setLogin(studentElement.getAttribute("login"));
        return serial;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public static void main(String[] args) {
        SerialDomBuilder domBuilder = new SerialDomBuilder();
        domBuilder.buildSetStudents("src\\main\\resources\\xml\\serials.xml");
        System.out.println(domBuilder.getSerials());
    }
}
