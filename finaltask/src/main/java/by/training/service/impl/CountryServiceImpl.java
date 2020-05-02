package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.service.CountryService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.CountryValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.utils.ConstantName.ERROR_LOGGER;

public class CountryServiceImpl implements CountryService {

    private static final Validation<Country> validator = new CountryValidation();

    @Override
    public Country findByName(String countryName) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Country result = DaoFactory.getInstance().getCountryDao(transaction).findByName(countryName);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Country> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Country> result = DaoFactory.getInstance().getCountryDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Country> findCountryPageByPage(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Country> result = DaoFactory.getInstance().getCountryDao(transaction).findCountryPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllCountry() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getCountryDao(transaction).countAllCountry();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCountryDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(Country country) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validator.isValid(transaction, country)) {
                if (country.getId() == 0) {
                    result = DaoFactory.getInstance().getCountryDao(transaction).create(country);
                } else {
                    result = DaoFactory.getInstance().getCountryDao(transaction).update(country);
                }
                transaction.commit();
                return result;
            } else {
                transaction.rollback();
                throw new ServiceException("Not valid country", HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
