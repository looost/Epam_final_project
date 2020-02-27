package by.training.main;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Test {
    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\xml\\serials.xml";
        String schemaName = "src\\main\\resources\\xml\\serials.xsd";
        String logName = "logs/log.txt";
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            // установка проверки с использованием XSD
            schema = factory.newSchema(new File(schemaName));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            // создание объекта-парсера
            SAXParser parser = spf.newSAXParser();
            // установка обработчика ошибок и запуск

            System.out.println(fileName + " is valid");
        } catch (ParserConfigurationException e) {
            System.err.println(fileName + " config error: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println(fileName + " SAX error: " + e.getMessage());
        }
    }
}
