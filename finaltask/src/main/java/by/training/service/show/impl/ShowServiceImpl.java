package by.training.service.show.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.show.SerialService;

import java.util.List;

public class ShowServiceImpl implements SerialService {

    // TODO логгер один и тот же или на каждый слой свой?

    @Override
    public Serial findSerialByName(String name) throws ServiceException {
        try {
            Serial serial = DaoFactory.getInstance().getSerialDao().findSerialByName(name);
            List<Genre> genreList = DaoFactory.getInstance().getGenreDao().findAll();
            for (Genre serialGenre : serial.getGenreList()
            ) {
                for (Genre g : genreList
                ) {
                    if (g.getId() == serialGenre.getId()) {
                        serialGenre.setName(g.getName());
                    }
                }
            }
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Serial findById(String id) throws ServiceException {
        try {
            Serial serial = DaoFactory.getInstance().getSerialDao().findById(id);
            List<Genre> genreList = DaoFactory.getInstance().getGenreDao().findAll();
            for (Genre serialGenre : serial.getGenreList()
            ) {
                for (Genre g : genreList
                ) {
                    if (g.getId() == serialGenre.getId()) {
                        serialGenre.setName(g.getName());
                    }
                }
            }
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(Serial entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Serial entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean update(Serial entity) throws ServiceException {
        return false;
    }
}
