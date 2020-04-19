package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.service.CountryService;
import by.training.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.utils.ConstantName.ERROR_LOGGER;

public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public List<Country> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Country> result = DaoFactory.getInstance().getCountryDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            logger.error(e);
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
            logger.error(e);
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
            logger.error(e);
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Country findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCountryDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean create(Country entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCountryDao(transaction).create(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean update(Country entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCountryDao(transaction).update(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
