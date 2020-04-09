package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.GenreService;
import by.training.service.exception.ServiceException;

import java.util.List;

public class GenreServiceImpl implements GenreService {


    @Override
    public Genre findByName(String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
//            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(transaction).findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
//            TransactionFactory transactionFactory = new TransactionFactory();
//            Transaction transaction = transactionFactory.createTransaction();
//            GenreDao genreDao = transaction.createDao(EntityEnum.GENRE);
//            return genreDao.findAll();
//            Connection connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getGenreDao(transaction).findAll();
        } catch (DaoException e) {
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
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            boolean res = DaoFactory.getInstance().getGenreDao(transaction).delete(id);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Genre entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            boolean res = DaoFactory.getInstance().getGenreDao(transaction).create(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Genre entity) throws ServiceException {
//        Connection connection = null;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            boolean res = DaoFactory.getInstance().getGenreDao(transaction).update(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
