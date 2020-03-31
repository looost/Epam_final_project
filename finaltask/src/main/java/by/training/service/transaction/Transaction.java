package by.training.service.transaction;

import by.training.dao.AbstractDao;
import by.training.dao.EntityEnum;
import by.training.service.exception.ServiceException;

import java.sql.Connection;

public interface Transaction {
    <T extends AbstractDao> T createDao(EntityEnum dao) throws ServiceException;

    void commit() throws ServiceException;

    void close() throws ServiceException;

    Connection getConnection();
}
