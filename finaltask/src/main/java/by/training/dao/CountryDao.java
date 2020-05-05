package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Country;

import java.util.List;

/**
 * Dao interface for {@link Country}, all method throws {@link DaoException}.
 */
public interface CountryDao extends AbstractDao<String, Country> {

    /**
     * Find country by name.
     *
     * @param countryName the country name
     * @return the country
     * @throws DaoException if the method failed
     */
    Country findByName(String countryName) throws DaoException;

    /**
     * Find all country.
     *
     * @return the list of countries
     * @throws DaoException if the method failed
     */
    List<Country> findAll() throws DaoException;

    /**
     * Find country page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of countries
     * @throws DaoException if the method failed
     */
    List<Country> findCountryPageByPage(int page, int limit) throws DaoException;

    /**
     * Count all country.
     *
     * @return number of all countries
     * @throws DaoException if the method failed
     */
    int countAllCountry() throws DaoException;
}
