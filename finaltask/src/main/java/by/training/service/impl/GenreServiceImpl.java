package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.GenreService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.impl.GenreValidation;
import by.training.service.validation.Validation;

import java.util.List;

/**
 * Implementation of {@link GenreService} interface. Provides access to {@link by.training.dao.GenreDao}
 * and provides support for working with the entity {@link Genre}.
 *
 * @see Transaction
 * @see DaoException
 */
public class GenreServiceImpl implements GenreService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Genre> VALIDATOR = new GenreValidation();

    /**
     * Find genre by name.
     *
     * @param name the genre name
     * @return the genre and null if does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Genre findByName(final String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Genre result = DaoFactory.getInstance().getGenreDao(transaction).findByName(name);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find all genres.
     *
     * @return the {@link List} of ${@link Genre} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Genre> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find genre page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Genre} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Genre> findGenrePageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Genre> result = DaoFactory.getInstance().getGenreDao(transaction).findGenrePageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count all genres.
     *
     * @return number of all genres
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllGenres() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getGenreDao(transaction).countAllGenres();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete genre.
     *
     * @param id the genre id
     * @return true if genre was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getGenreDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save genre.
     *
     * @param genre the genre
     * @return true if genre was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Genre genre) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (VALIDATOR.isValid(transaction, genre)) {
                if (genre.getId() == 0) {
                    result = DaoFactory.getInstance().getGenreDao(transaction).create(genre);
                } else {
                    result = DaoFactory.getInstance().getGenreDao(transaction).update(genre);
                }
                transaction.commit();
                return result;
            } else {
                transaction.rollback();
                throw new ServiceException("Not valid genre", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
