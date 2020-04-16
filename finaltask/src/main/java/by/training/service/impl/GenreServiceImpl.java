package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.GenreService;
import by.training.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LogManager.getLogger("debug");

    @Override
    public Genre findByName(String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Genre result = DaoFactory.getInstance().getGenreDao(transaction).findByName(name);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> findGenrePageByPage(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findGenrePageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllGenres() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getGenreDao(transaction).countAllGenres();
            transaction.commit();
            return result;
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
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Genre entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (findByName(entity.getName()) != null) {
                //throw new ValidationException("A genre with the same name already exists");
                throw new ServiceException("A genre with the same name already exists");
            }
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).create(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Genre entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).update(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
