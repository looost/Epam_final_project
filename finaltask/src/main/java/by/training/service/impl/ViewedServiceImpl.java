package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.service.ViewedService;
import by.training.service.exception.ServiceException;

import java.sql.Connection;

public class ViewedServiceImpl implements ViewedService {
    @Override
    public boolean create(String userId, String serialId) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getViewedDao(connection).create(userId, serialId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean delete(String userId, String serialId) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getViewedDao(connection).delete(userId, serialId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
