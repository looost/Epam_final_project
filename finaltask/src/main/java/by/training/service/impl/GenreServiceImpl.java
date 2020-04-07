package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.GenreService;
import by.training.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenreServiceImpl implements GenreService {

    private final Transaction transaction;

    public GenreServiceImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Genre findByName(String name) throws ServiceException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).findByName(name);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            TransactionFactory transactionFactory = new TransactionFactory();
//            Transaction transaction = transactionFactory.createTransaction();
//            GenreDao genreDao = transaction.createDao(EntityEnum.GENRE);
//            return genreDao.findAll();
//            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).findAll();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Genre findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).delete(id);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Genre entity) throws ServiceException {
        try (Connection connection = transaction.getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            boolean res = DaoFactory.getInstance().getGenreDao(connection).create(entity);
            transaction.commit(connection);
            return res;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Genre entity) throws ServiceException {
//        Connection connection = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(connection).update(entity);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
