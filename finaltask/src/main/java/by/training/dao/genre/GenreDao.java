package by.training.dao.genre;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.entity.Entity;
import by.training.entity.Genre;

import java.util.List;

public interface GenreDao extends AbstractDao<String, Genre> {
    Genre findByName(String name) throws DaoException;
}
