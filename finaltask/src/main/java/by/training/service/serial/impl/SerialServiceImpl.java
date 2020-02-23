package by.training.service.serial.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.*;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.serial.SerialService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class SerialServiceImpl implements SerialService {

    @Override
    public Serial findSerialByName(String name) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findSerialByName(name);
            List<Genre> genreList = DaoFactory.getInstance().getGenreDao(connection).findAll();
            for (Genre serialGenre : serial.getGenres()
            ) {
                for (Genre g : genreList
                ) {
                    if (g.getId() == serialGenre.getId()) {
                        serialGenre.setName(g.getName());
                        break;
                    }
                }
            }

            Country country = DaoFactory.getInstance().getCountryDao(connection).findById(String.valueOf(serial.getCountry().getId()));
            serial.getCountry().setName(country.getName());

            Studio studio = DaoFactory.getInstance().getStudioDao(connection).findById(String.valueOf(serial.getStudio().getId()));
            serial.getStudio().setName(studio.getName());

            Set<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForSerial(String.valueOf(serial.getId()));
            serial.setComments(commentSet);

            connection.commit();
            ConnectionPool.getInstance().close(connection);
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            throw new ServiceException("Commit problem", e);
        }
    }

    @Override
    public List<Serial> findAll() throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            List <Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findAll();
            int size = serialList.size();
            for (int i = 0; i < size; i++) {
                serialList.set(i, findSerialByName(serialList.get(i).getName()));
            }
            connection.commit();
            ConnectionPool.getInstance().close(connection);
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            throw new ServiceException("Commit problem", e);
        }
    }

    @Override
    public Serial findById(String id) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById(id);
            serial = findSerialByName(serial.getName());
            connection.commit();
            ConnectionPool.getInstance().close(connection);
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            throw new ServiceException("Cannot auto commit", e);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean create(Serial entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Serial entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
