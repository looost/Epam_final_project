package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Country;

import java.util.List;

public interface CountryDao extends AbstractDao<String, Country> {

    Country findByName(String countryName) throws DaoException;

    List<Country> findAll() throws DaoException;

    List<Country> findCountryPageByPage(int page, int limit) throws DaoException;

    int countAllCountry() throws DaoException;
}
