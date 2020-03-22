package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
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
    public Genre findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Genre entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Genre entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
