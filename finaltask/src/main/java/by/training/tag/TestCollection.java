package by.training.tag;

import by.training.model.Genre;

import java.util.Iterator;
import java.util.List;

public class TestCollection {
    private List<Genre> genres;

    public TestCollection(List<Genre> genres) {
        genres.forEach(System.out::println);
        this.genres = genres;
    }

    public int getSize() {
        return genres.size();
    }

    public String getValue() {
        Iterator<Genre> iterator = genres.iterator();
        if (iterator.hasNext()) {
            return iterator.next().toString();
        } else {
            return null;
        }
    }
}
