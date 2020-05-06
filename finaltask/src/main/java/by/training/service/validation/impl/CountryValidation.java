package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;

import static by.training.utils.ConstantName.MAX_COUNTRY_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Country}.
 */
public class CountryValidation implements Validation<Country> {

    @Override
    public boolean isValid(final Transaction transaction, final Country entity) throws ServiceException {
        try {
            return DaoFactory.getInstance()
                    .getCountryDao(transaction)
                    .findByName(entity.getName()) == null
                    && !entity.getName().equals("")
                    && entity.getName().length() <= MAX_COUNTRY_NAME_LENGTH;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
