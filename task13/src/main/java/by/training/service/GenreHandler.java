package by.training.service;

import by.training.entity.Genre;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GenreHandler extends DefaultHandler {
    private Set<Genre> genres;
    private Genre current;
    private GenreEnum currentEnum = null;
    private EnumSet<GenreEnum> withText;

    public GenreHandler() {
        this.genres = new HashSet<>();
        withText = EnumSet.range(GenreEnum.NAME, GenreEnum.NAME);
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("genre".equals(localName)) {
            //System.err.println("Новый жанр");
            current = new Genre();
            current.setId(Integer.parseInt(attributes.getValue(0)));

        } else {
            //System.out.println(localName);
            try {
                GenreEnum temp = GenreEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            } catch (IllegalArgumentException e) {
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        if (currentEnum != null && current != null) {
            System.err.println(s);
            current.setName(s);
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("genre".equals(localName)) {
            //System.err.println("Добавляем жанр в set -------> " + current);
            Genre genre = new Genre();
            genre.setId(current.getId());
            genre.setName(current.getName());
            genres.add(genre);
            current = null;
            //genres.forEach(g -> System.err.println(g));
        }
    }
}
