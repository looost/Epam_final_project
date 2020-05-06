package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;

import static by.training.utils.ConstantName.MAX_GENRE_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Genre}.
 */
public class GenreValidation implements Validation<Genre> {

    @Override
    public boolean isValid(final Transaction transaction, final Genre entity) throws ServiceException {
        try {
            return DaoFactory.getInstance()
                    .getGenreDao(transaction)
                    .findByName(entity.getName()) == null
                    && !entity.getName().equals("")
                    && entity.getName().length() <= MAX_GENRE_NAME_LENGTH;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
