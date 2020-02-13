package by.training.service.genre.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Entity;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.genre.GenreService;

import java.util.List;

public class GenreServiceImpl implements GenreService {
    @Override
    public Genre findByName(String name) throws ServiceException {
        try {
            return DaoFactory.getInstance().getGenreDao().findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List findAll() throws ServiceException {
        return null;
    }

    @Override
    public Entity findById(Object id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(Object id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Entity entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Entity entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws ServiceException {
        return false;
    }
}
