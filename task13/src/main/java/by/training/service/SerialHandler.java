package by.training.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SerialHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String s = localName;
        System.err.println(localName);
        for (int i = 0; i < attributes.getLength(); i++) {
            s += " " + attributes.getLocalName(i) + "=" + attributes.getValue(i);
        }
        System.out.print(s.trim());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(" " + new String(ch, start, length) + " ");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print(localName);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nParsing ended");
    }
}
