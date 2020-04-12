package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Genre;

import java.util.List;

public interface GenreDao extends AbstractDao<String, Genre> {
    List<Genre> findAll() throws DaoException;

    List<Genre> findGenrePageByPage(int page, int limit) throws DaoException;

    int countAllGenres() throws DaoException;
    Genre findByName(String name) throws DaoException;
    List<Genre> findGenreBySerialId(String serialId) throws DaoException;
}
