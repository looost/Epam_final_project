package by.training.dao.genre;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.Genre;

public interface GenreDao extends AbstractDao<String, Genre> {
    Genre findByName(String name) throws DaoException;
}
