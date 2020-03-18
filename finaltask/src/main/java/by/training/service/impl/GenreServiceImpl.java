package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.AbstractEntity;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.GenreService;

import java.sql.Connection;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    @Override
    public Genre findByName(String name) throws ServiceException {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AbstractEntity findById(Object id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(Object id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(AbstractEntity abstractEntity) throws ServiceException {
        return false;
    }

    @Override
    public boolean update(AbstractEntity abstractEntity) throws ServiceException {
        return false;
    }
}
