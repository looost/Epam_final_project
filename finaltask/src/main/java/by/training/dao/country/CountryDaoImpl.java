package by.training.dao.country;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.Country;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CountryDaoImpl implements AbstractDao<String, Country> {

    private static final String PATH_TO_PROPERTIES = "E:\\Java-Training\\finaltask\\src\\main\\resources\\sqlCountry.properties";

    private Connection connection;

    public CountryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> findAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllCountry"));
            resultSet = statement.executeQuery();
            List<Country> countryList = new ArrayList<>();
            while (resultSet.next()) {
                countryList.add(new Country(resultSet.getInt(getProperties().getProperty("countryId")), resultSet.getString(getProperties().getProperty("countryName"))));
            }
            return countryList;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public Country findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findCountryById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Country country = null;
            while (resultSet.next()) {
                country = new Country(resultSet.getInt(getProperties().getProperty("countryId")), resultSet.getString(getProperties().getProperty("countryName")));
            }
            return country;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public boolean delete(String id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("deleteCountryById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Country entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createCountry"));
            statement.setString(1, entity.getName());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(Country entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateCountry"));
            statement.setString(1, entity.getName());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    private void close(ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close resultSet", e);
        }
    }

    private void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close statement", e);
        }
    }

    private Properties getProperties() throws DaoException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
            return properties;
        } catch (IOException e) {
            throw new DaoException("Not found properties file", e);
        }
    }
}
