package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;
import by.training.service.SerialService;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.TransactionHandler;
import by.training.service.transaction.TransactionHandlerFactory;
import by.training.service.transaction.TransactionUtil;

import java.sql.Connection;
import java.util.List;

public class SerialServiceImpl implements SerialService {

    private static final TransactionHandler<Serial> SERIAL_TRANSACTION_HANDLER = TransactionHandlerFactory.SERIAL_TRANSACTION_HANDLER;
    private static final TransactionHandler<Serial> CREATE_SERIAL_WITH_GENRE = TransactionHandlerFactory.CREATE_SERIAL_WITH_GENRE;

    @Override
    public Serial findSerialByName(String name) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findSerialByName(name);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getSingleTransactionHandler(SERIAL_TRANSACTION_HANDLER), serial);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findAll() throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findAll();
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findAllSerial2(int page, int limit) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findAllSerial2(page, limit);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findSerialBySearchForm(String searchQuery) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findSerialBySearchForm(searchQuery);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Serial findById(String id) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById(id);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getSingleTransactionHandler(SERIAL_TRANSACTION_HANDLER), serial);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
//            connection.setAutoCommit(false);
//            DaoFactory.getInstance().getSerialDao(connection).create(entity);
//            Serial serial = findSerialByName(entity.getName());
//            serial.setGenres(entity.getGenres());
//            DaoFactory.getInstance().getSerialGenreDao(connection).create(serial);
//            connection.commit();
//            ConnectionPool.getInstance().close(connection);
            TransactionUtil
                    .create(connection, TransactionHandlerFactory.getSingleTransactionHandler(CREATE_SERIAL_WITH_GENRE), entity);
            return true;
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
