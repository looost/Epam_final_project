package by.training.dao.impl;

import by.training.dao.CountryDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

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

    private Transaction transaction;

    public CountryDaoImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_ALL_COUNTRY = "SELECT id, name FROM country";
    @Override
    public List<Country> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_COUNTRY,
                ResultSetHandlerFactory.getListResultSetHandler(COUNTRY_RESULT_SET_HANDLER));
    }

    private static final String FIND_COUNTRY_BY_ID = "SELECT id, name FROM country WHERE id = ?";
    @Override
    public Country findById(String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_COUNTRY_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(COUNTRY_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_COUNTRY_BY_ID = "DELETE FROM country WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(transaction.getConnection(), DELETE_COUNTRY_BY_ID, id);
    }

    private static final String CREATE_COUNTRY = "INSERT INTO country VALUES (DEFAULT, ?)";
    @Override
    public boolean create(Country entity) throws DaoException {
        return JDBCUtil.create(transaction.getConnection(), CREATE_COUNTRY, entity.getName());
    }

    private static final String UPDATE_COUNTRY = "UPDATE country SET name = ? WHERE id = ?";
    @Override
    public boolean update(Country entity) throws DaoException {
        return JDBCUtil.update(transaction.getConnection(), UPDATE_COUNTRY, entity.getName());
    }
}
