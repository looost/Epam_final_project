package by.training.service;

import by.training.model.Country;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface CountryService extends AbstractService<String, Country> {
    List<Country> findAll() throws ServiceException;
}
