package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Country;

import java.util.List;

public interface CountryDao extends AbstractDao<String, Country> {

    List<Country> findAll() throws DaoException;
}
