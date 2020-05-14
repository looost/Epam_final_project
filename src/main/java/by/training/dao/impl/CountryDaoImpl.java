package by.training.dao.impl;

import by.training.dao.CountryDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link CountryDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Country}.
 *
 * @see Transaction
 * @see DaoException
 */
public class CountryDaoImpl implements CountryDao {

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Country} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Country> COUNTRY_RESULT_SET_HANDLER = new ResultSetHandler<Country>() {
        @Override
        public Country handle(final ResultSet rs) throws DaoException {
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

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    public CountryDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_COUNTRY_BY_NAME = "SELECT id, name FROM country WHERE name = ?";

    /**
     * Find country by name.
     *
     * @param countryName the country name
     * @return the ${@link Country} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Country findByName(final String countryName) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_COUNTRY_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(COUNTRY_RESULT_SET_HANDLER), countryName);
    }

    private static final String FIND_ALL_COUNTRY = "SELECT id, name FROM country";

    /**
     * Find all country.
     *
     * @return the list of countries and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Country> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_COUNTRY,
                ResultSetHandlerFactory.getListResultSetHandler(COUNTRY_RESULT_SET_HANDLER));
    }

    private static final String FIND_COUNTRY_PAGE_BY_PAGE = "SELECT id, name FROM country ORDER BY name LIMIT ? OFFSET ?";

    /**
     * Find country page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of countries and and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Country> findCountryPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_COUNTRY_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(COUNTRY_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String COUNT_ALL_COUNTRY = "SELECT COUNT(*) FROM country";

    /**
     * Count all country.
     *
     * @return number of all countries
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllCountry() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_COUNTRY,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    private static final String FIND_COUNTRY_BY_ID = "SELECT id, name FROM country WHERE id = ?";

    /**
     * Find country by id.
     *
     * @param id the country id
     * @return the ${@link Country} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Country findById(final String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_COUNTRY_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(COUNTRY_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_COUNTRY_BY_ID = "DELETE FROM country WHERE id = ?";

    /**
     * Delete country by id.
     *
     * @param id the country id
     * @return true if country was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_COUNTRY_BY_ID, id);
    }

    private static final String CREATE_COUNTRY = "INSERT INTO country VALUES (DEFAULT, ?)";

    /**
     * Create country.
     *
     * @param entity the country
     * @return true if country was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Country entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_COUNTRY, entity.getName());
    }

    private static final String UPDATE_COUNTRY = "UPDATE country SET name = ? WHERE id = ?";

    /**
     * Update country.
     *
     * @param entity the country
     * @return true if country was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Country entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_COUNTRY, entity.getName(), entity.getId());
    }
}
