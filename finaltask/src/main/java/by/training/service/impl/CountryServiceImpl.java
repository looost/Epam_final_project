package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.service.CountryService;
import by.training.service.exception.ServiceException;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    @Override
    public List<Country> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
//            Transaction transaction = new Transaction();
//            CountryDao countryDao = DaoFactory.getInstance().getCountryDao();
//            transaction.startTransaction((Dao) countryDao);
//            List<Country> countryList = countryDao.findAll();
//            transaction.commit();
//            transaction.close();
            return DaoFactory.getInstance().getCountryDao(transaction).findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Country findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean res = DaoFactory.getInstance().getCountryDao(transaction).delete(id);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Country entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean res = DaoFactory.getInstance().getCountryDao(transaction).create(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Country entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean res = DaoFactory.getInstance().getCountryDao(transaction).update(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
