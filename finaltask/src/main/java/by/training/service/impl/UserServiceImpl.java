package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.UserService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public User findByLogin(String login) throws ServiceException {
//        Connection connection;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getUserDao(transaction).findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
//        Connection connection;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getUserDao(transaction).findByLoginAndPassword(login, password);
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
//        Connection connection;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            if (Validation.isCorrectUserLogin(transaction, entity)) {
                DaoFactory.getInstance().getUserDao(transaction).create(entity);
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
