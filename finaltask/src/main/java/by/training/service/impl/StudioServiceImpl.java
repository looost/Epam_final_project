package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Studio;
import by.training.service.StudioService;
import by.training.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudioServiceImpl implements StudioService {
    @Override
    public List<Studio> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            return DaoFactory.getInstance().getStudioDao(transaction).findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Studio findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Studio entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean update(Studio entity) throws ServiceException {
        return false;
    }
}
