package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.SerialService;
import by.training.service.exception.ServiceException;
import by.training.dao.Transaction;
import by.training.service.transaction.TransactionHandler;
import by.training.service.transaction.TransactionHandlerFactory;
import by.training.service.transaction.TransactionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SerialServiceImpl implements SerialService {

    private static final TransactionHandler<Serial> SERIAL_TRANSACTION_HANDLER = TransactionHandlerFactory.SERIAL_TRANSACTION_HANDLER;
    private static final TransactionHandler<Serial> CREATE_SERIAL_WITH_GENRE = TransactionHandlerFactory.CREATE_SERIAL_WITH_GENRE;

    private final Transaction transaction;

    public SerialServiceImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Serial findSerialByName(String name) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findSerialByName(name);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getSingleTransactionHandler(SERIAL_TRANSACTION_HANDLER), serial);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findAll() throws ServiceException {
        //Connection connection;
        try (Connection connection = transaction.getConnection()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findAll();
            serialList = TransactionUtil.select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit(connection);
            return serialList;
//            return TransactionUtil
//                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findAllSerial2(int page, int limit) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findAllSerial2(page, limit);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findSerialBySearchForm(String searchQuery) throws ServiceException {   //TODO deprecated
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findSerialBySearchForm(searchQuery);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findSerialBySearchForm(SearchForm searchForm) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            if (searchForm.getQuery() == null) {
                searchForm.setQuery("");
            }
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findSerialBySearchForm(searchForm);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findSerialByGenre(String genreId) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findSerialByGenre(genreId);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Serial> findSerialsThatIWatch(String userId) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(connection).findSerialsThatIWatch(userId);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(SERIAL_TRANSACTION_HANDLER), serialList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException();
        }
    }

    @Override
    public boolean toWatchSerial(String userId, String serialId) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).toWatchSerial(userId, serialId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean stopWatchSerial(String userId, String serialId) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).stopWatchSerial(userId, serialId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Serial findById(String id) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById(id);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getSingleTransactionHandler(SERIAL_TRANSACTION_HANDLER), serial);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).delete(id);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Serial entity) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = transaction.getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();

//            TransactionFactory transactionFactory = new TransactionFactory();
//            Transaction transaction = transactionFactory.createTransaction();
//            SerialDao serialDao = transaction.createDao(EntityEnum.SERIAL);
//            int index = serialDao.createAndReturnIndex(entity);
//            serialDao.createSerialGenre(index, entity.getGenres());
//            transaction.commit();
//            System.out.println(transaction.getConnection() + "!");
//            transaction.close();
//            System.out.println(transaction.getConnection());
//            return true;
            TransactionUtil
                    .create(connection, TransactionHandlerFactory.getSingleTransactionHandler(CREATE_SERIAL_WITH_GENRE), entity);
            transaction.commit(connection);
            return true;
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Serial entity) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getSerialDao(connection).update(entity);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
