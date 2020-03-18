package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.UserService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    @Override
    public User findByLogin(String login) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getUserDao(connection).findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getUserDao(connection).findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createUserWithRole(User user) throws ServiceException {
        return false;
    }

    @Override
    public User findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(User entity) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if (Validation.isCorrectUserLogin(connection, entity)) {
                DaoFactory.getInstance().getUserDao(connection).create(entity);
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        return false;
    }
}
