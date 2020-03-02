package by.training.service.builder;

import by.training.entity.Serial;
import by.training.service.SerialHandler2;
import by.training.service.SerialHandler3;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class SerialSAXBuilder {
    private Set<Serial> serials;
    //private SerialHandler2 sh;
    private SerialHandler3 sh;
    private XMLReader reader;

    public SerialSAXBuilder() {
        // создание SAX-анализатора
        sh = new SerialHandler3();
        try {
            // создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    public void buildSetSerials(String fileName) {
        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        serials = sh.getSerials();
    }
}
