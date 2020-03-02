package by.training.service.builder;

import by.training.entity.Genre;

public abstract class BaseBuilder {

    protected Genre genre = new Genre();

    public Genre getGenre() {
        return this.genre;
    }

    public abstract void buildGenres(String fileName);
}
