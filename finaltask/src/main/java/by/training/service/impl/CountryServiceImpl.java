package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.service.CountryService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.CountryValidation;

import java.util.List;

/**
 * Implementation of {@link CountryService} interface. Provides access to {@link by.training.dao.CountryDao}
 * and provides support for working with the entity {@link Country}.
 *
 * @see Transaction
 * @see DaoException
 */
public class CountryServiceImpl implements CountryService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Country> VALIDATOR = new CountryValidation();

    /**
     * Find country by name.
     *
     * @param countryName the country name
     * @return the country and null if does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Country findByName(final String countryName) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Country result = DaoFactory.getInstance().getCountryDao(transaction).findByName(countryName);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find all country.
     *
     * @return the {@link List} of ${@link Country} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Country> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Country> result = DaoFactory.getInstance().getCountryDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find country page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Country} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Country> findCountryPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Country> result = DaoFactory.getInstance().getCountryDao(transaction).findCountryPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count all country.
     *
     * @return number of all countries
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllCountry() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getCountryDao(transaction).countAllCountry();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete country.
     *
     * @param id the country id
     * @return true if country was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCountryDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save country.
     *
     * @param country the country
     * @return true if country was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Country country) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (VALIDATOR.isValid(transaction, country)) {
                if (country.getId() == 0) {
                    result = DaoFactory.getInstance().getCountryDao(transaction).create(country);
                } else {
                    result = DaoFactory.getInstance().getCountryDao(transaction).update(country);
                }
                transaction.commit();
                return result;
            } else {
                transaction.rollback();
                throw new ServiceException("Not valid country", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
