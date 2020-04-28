package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.GenreService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.GenreValidation;
import by.training.service.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.utils.ConstantName.DEBUG_LOGGER;

public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);
    private static final Validation<Genre> validation = new GenreValidation();

    @Override
    public Genre findByName(String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Genre result = DaoFactory.getInstance().getGenreDao(transaction).findByName(name);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Genre> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Genre> findGenrePageByPage(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findGenrePageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllGenres() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getGenreDao(transaction).countAllGenres();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean create(Genre entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (validation.isValid(transaction, entity)) {
                throw new ServiceException("Not valid data", HttpServletResponse.SC_BAD_REQUEST);
            }
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).create(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean update(Genre entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).update(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(Genre genre) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validation.isValid(transaction, genre)) {
                if (genre.getId() == 0) {
                    result = DaoFactory.getInstance().getGenreDao(transaction).create(genre);
                } else {
                    result = DaoFactory.getInstance().getGenreDao(transaction).update(genre);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid data", HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
