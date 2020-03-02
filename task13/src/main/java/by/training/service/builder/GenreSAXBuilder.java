package by.training.service.builder;

import by.training.entity.Genre;
import by.training.entity.Serial;
import by.training.service.GenreHandler;
import by.training.service.SerialHandler2;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class GenreSAXBuilder extends BaseBuilder {
    private Set<Genre> genres;
    private GenreHandler gh;
    private XMLReader reader;

    public GenreSAXBuilder() {
        // создание SAX-анализатора
        gh = new GenreHandler();
        try {
            // создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(gh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public void buildGenres(String fileName) {
        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        genres = gh.getGenres();
    }
}
