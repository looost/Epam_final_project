package by.training.main;

import by.training.service.SerialHandler;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialDomBuilder;
import by.training.service.builder.SerialSAXBuilder;
import by.training.service.builder.SerialStAXBuilder;
import by.training.service.validation.ValidationXML;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

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

//        SerialSAXBuilder saxBuilder = new SerialSAXBuilder();
//        saxBuilder.buildSetSerials("src\\main\\resources\\xml\\serials.xml");
//        for (Serial s : saxBuilder.getSerials()
//        ) {
//            System.out.println(s);
//        }
        String path = "src\\main\\resources\\xml\\serials.xml";
        String schema = "src\\main\\resources\\xml\\serials.xsd";


        System.out.println(ValidationXML.xmlIsValid(path, schema));

//        System.out.println("SAX Builder:");
//        BaseBuilder builder = new SerialSAXBuilder();
//        builder.buildSetSerials(path);
//        builder.getSerials().forEach(System.out::println);
//        System.out.println("-----");
//
//        System.out.println("StAX Builder:");
//        BaseBuilder builder1 = new SerialStAXBuilder();
//        builder1.buildSetSerials(path);
//        builder1.getSerials().forEach(System.out::println);
//        System.out.println("-----");

        System.out.println("DOM Builder:");
        BaseBuilder builder2 = new SerialDomBuilder();
        builder2.buildSetSerials(path);
        builder2.getSerials().forEach(System.out::println);
        System.out.println("-----");

//        GenreSAXBuilder saxBuilder = new GenreSAXBuilder();
//        saxBuilder.buildGenres("src\\main\\resources\\xml\\serials.xml");
//
//        for (Genre g: saxBuilder.getGenres()
//             ) {
//            System.out.println(g);
//        }

    }
}
