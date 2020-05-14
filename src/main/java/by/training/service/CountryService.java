package by.training.service;

import by.training.model.Country;
import by.training.service.exception.ServiceException;

import java.util.List;

/**
 * The service interface for {@link Country}, all method throws {@link ServiceException}.
 */
public interface CountryService {

    /**
     * Find country by name.
     *
     * @param countryName the country name
     * @return the country
     * @throws ServiceException if the method failed
     */
    Country findByName(String countryName) throws ServiceException;

    /**
     * Find all country.
     *
     * @return the list of countries
     * @throws ServiceException if the method failed
     */
    List<Country> findAll() throws ServiceException;

    /**
     * Find country page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of countries
     * @throws ServiceException if the method failed
     */
    List<Country> findCountryPageByPage(int page, int limit) throws ServiceException;

    /**
     * Count all country.
     *
     * @return number of all countries
     * @throws ServiceException if the method failed
     */
    int countAllCountry() throws ServiceException;

    /**
     * Delete country.
     *
     * @param id the country id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save country.
     *
     * @param country the country
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Country country) throws ServiceException;
}
