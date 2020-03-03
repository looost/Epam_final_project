package by.training.service.builder;

import by.training.service.SerialHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SerialSAXBuilder extends BaseBuilder {

    private Logger logger = LogManager.getLogger("logger");
    private SerialHandler sh;
    private XMLReader reader;

    public SerialSAXBuilder() {
        // создание SAX-анализатора
        sh = new SerialHandler();
        try {
            // создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            logger.error("ошибка SAX парсера: " + e);
        }
    }

    @Override
    public void buildSetSerials(String fileName) {

        System.out.println("РАБОТАЕТ SAX BUILDER");

        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.error("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            logger.error("ошибка I/О потока: " + e);
        }
        serials = sh.getSerials();
    }
}
