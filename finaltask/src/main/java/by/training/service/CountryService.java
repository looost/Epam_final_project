package by.training.service;

import by.training.model.Country;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface CountryService {

    Country findByName(String countryName) throws ServiceException;

    List<Country> findAll() throws ServiceException;

    List<Country> findCountryPageByPage(int page, int limit) throws ServiceException;

    int countAllCountry() throws ServiceException;

    boolean delete(String id) throws ServiceException;

    boolean save(Country country) throws ServiceException;
}
