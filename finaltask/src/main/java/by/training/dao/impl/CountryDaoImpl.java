package by.training.dao.impl;

import by.training.dao.CountryDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Country;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class CountryDaoImpl implements CountryDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlCountry.properties";

    private static final ResultSetHandler<Country> COUNTRY_RESULT_SET_HANDLER = new ResultSetHandler<Country>() {
        @Override
        public Country handle(ResultSet rs) throws DaoException {
            Country country = new Country();
            try {
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                return country;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    private Connection connection;

    public CountryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> findAll() throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findAllCountry"),
                ResultSetHandlerFactory.getListResultSetHandler(COUNTRY_RESULT_SET_HANDLER));
    }

    @Override
    public Country findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findCountryById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(COUNTRY_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteCountryById"), id);
    }

    @Override
    public boolean create(Country entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createCountry"), entity.getName());
    }

    @Override
    public boolean update(Country entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateCountry"), entity.getName());
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
