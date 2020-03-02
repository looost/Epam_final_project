package by.training.main;

import by.training.entity.Genre;
import by.training.entity.Serial;
import by.training.service.SerialHandler;
import by.training.service.builder.GenreSAXBuilder;
import by.training.service.builder.SerialSAXBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
//        try {
//            // создание SAX-анализатора
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//            SerialHandler handler = new SerialHandler();
//            reader.setContentHandler(handler);
//            reader.parse("src\\main\\resources\\xml\\serials.xml");
//        } catch (SAXException e) {
//            System.err.print("ошибка SAX парсера " + e);
//        } catch (IOException e) {
//            System.err.print("ошибка I/О потока " + e);
//        }

        SerialSAXBuilder saxBuilder = new SerialSAXBuilder();
        saxBuilder.buildSetSerials("src\\main\\resources\\xml\\serials.xml");
        for (Serial s : saxBuilder.getSerials()
        ) {
            System.out.println(s);
        }


//        GenreSAXBuilder saxBuilder = new GenreSAXBuilder();
//        saxBuilder.buildGenres("src\\main\\resources\\xml\\serials.xml");
//
//        for (Genre g: saxBuilder.getGenres()
//             ) {
//            System.out.println(g);
//        }

    }
}
