package by.training.service;

import by.training.model.Genre;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface GenreService {
    List<Genre> findAll() throws ServiceException;

    List<Genre> findGenrePageByPage(int page, int limit) throws ServiceException;

    int countAllGenres() throws ServiceException;

    Genre findByName(String name) throws ServiceException;

    boolean delete(String id) throws ServiceException;

    boolean save(Genre genre) throws ServiceException;
}
