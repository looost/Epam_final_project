package by.training.dao.impl;

import by.training.dao.CountryDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Country;
import by.training.model.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static by.training.utils.ConstantName.DEBUG_LOGGER;
import static by.training.utils.ConstantName.PATH_TO_PROPERTY_FILE;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CountryDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private CountryDao dao;

    public CountryDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new CountryDaoImpl(transaction);
    }

    @BeforeTest
    public void init() throws DaoException {
        try {
            Properties property = new Properties();
            property.load(new FileInputStream(PATH_TO_PROPERTY_FILE));
            ConnectionPool.getInstance()
                    .init(property.getProperty("DB_DRIVER_CLASS"), property.getProperty("DB_URL"), property.getProperty("DB_USER"),
                            property.getProperty("DB_PASSWORD"), Integer.parseInt(property.getProperty("DB_POOL_START_SIZE")),
                            Integer.parseInt(property.getProperty("DB_POOL_MAX_SIZE")), Integer.parseInt(property.getProperty("DB_POOL_CHECK_CONNECTION_TIMEOUT")));
            connectionPool = ConnectionPool.getInstance();
        } catch (FileNotFoundException e) {
            throw new DaoException("Property file not fount", e);
        } catch (IOException e) {
            throw new DaoException("Cannot load file ", e);
        }
    }

    @DataProvider(name = "dataForFindAll")
    public Object[] createPositiveDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Country(1, "США"),
                        new Country(2, "Великобритания"), new Country(3, "Испания"))
        };
    }

    @Test(dataProvider = "dataForFindAll")
    public void testFindAll(List<Country> expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        List<Country> actual = dao.findAll();
        actual.sort(Comparator.comparing(Country::getId));
        expected.sort(Comparator.comparing(Country::getId));
        assertEquals(actual, expected);
        connection.close();
    }

    @DataProvider(name = "negativeDataForFindAll")
    public Object[] createNegativeDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Country(1, "США"),
                        new Country(2, "Великобритания"), new Country(3, "Испания")),
                null,
                new ArrayList<Country>(),
                new ArrayList<Integer>()
        };
    }

    @Test(dataProvider = "negativeDataForFindAll")
    public void testFindAllNegative(List<Country> expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertNotEquals(dao.findAll(), expected);
        connection.close();
    }

    @DataProvider(name = "dataForFindCountryPageByPage")
    public Object[] createPositiveDataForFindCountryPageByPage() {
        return new Object[][]{
                {1, 1, Collections.singletonList(new Country(2, "Великобритания"))},
                {1, 2, Collections.singletonList(new Country(3, "Испания"))},
                {2, 2, Collections.singletonList(new Country(1, "США"))},
                {0, 2, Collections.emptyList()},
                {0, 0, Collections.emptyList()},
                {3, 1, Arrays.asList(new Country(2, "Великобритания"), new Country(3, "Испания"),
                        new Country(1, "США"))}
        };
    }

    @Test(dataProvider = "dataForFindCountryPageByPage")
    public void testFindCountryPageByPage(int limit, int page, List<Genre> expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        List<Country> actual = dao.findCountryPageByPage(page, limit);
        assertEquals(actual, expected);
        connection.close();
    }

    @DataProvider(name = "negativeDataForFindCountryPageByPage")
    public Object[] createNegativeDataForFindCountryPageByPage() {
        return new Object[][]{
                {-1, 1},
                {2, -12},
                {-3, -1},
                {Integer.MIN_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, -2}
        };
    }

    @Test(dataProvider = "negativeDataForFindCountryPageByPage", expectedExceptions = DaoException.class)
    public void testFindCountryPageByPageWithNegativeValue(int limit, int page) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        dao.findCountryPageByPage(page, limit);
        connection.close();
    }

    private static final int COUNT_ALL_COUNTRY = 3;

    @Test
    public void testCountAllCountry() throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertEquals(dao.countAllCountry(), COUNT_ALL_COUNTRY);
        connection.close();
    }

    @DataProvider(name = "negativeDataForCountAllCountry")
    public Object[] createNegativeDataForCountAllCountry() {
        return new Object[]{
                1, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE
        };
    }

    @Test(dataProvider = "negativeDataForCountAllCountry")
    public void testCountAllCountryWithNegativeValue(int expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertNotEquals(dao.countAllCountry(), expected);
        connection.close();
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", new Country(1, "США")},
                {"2", new Country(2, "Великобритания")},
                {"3", new Country(3, "Испания")},
                {"0", null},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, Country expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        Country actual = dao.findById(id);
        assertEquals(actual, expected);
        connection.close();
    }

    @DataProvider(name = "positiveDelete")
    public Object[] createDataForDelete() {
        return new Object[]{
                "1", "2", "3"
        };
    }

    @Test(dataProvider = "positiveDelete")
    public void testDelete(String id) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        connection.setAutoCommit(false);
        assertTrue(dao.delete(id));
        connection.rollback();
        connection.close();
    }

    @DataProvider(name = "negativeDelete")
    public Object[] createNegativeDataForDelete() {
        return new Object[]{
                "4", null, "4.5", ""
        };
    }

    @Test(dataProvider = "negativeDelete")
    public void testNegativeDelete(String id) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertFalse(dao.delete(id));
        connection.close();
    }

    @DataProvider(name = "negativeDeleteWithException")
    public Object[] createNegativeDataForDeleteWithException() {
        return new Object[]{
                "String", "1f", "qq"
        };
    }

    @Test(dataProvider = "negativeDeleteWithException", expectedExceptions = DaoException.class)
    public void testNegativeDeleteWithException(String id) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertFalse(dao.delete(id));
        connection.close();
    }

    @DataProvider(name = "positiveCreate")
    public Object[] createPositiveDataForCreate() {
        return new Object[]{
                new Country(4, "TestName"),
                new Country(5, "AnyName"),
                new Country(Integer.MAX_VALUE, "MaxValue"),
                new Country(Integer.MIN_VALUE, "MinValue")
        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(Country country) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        connection.setAutoCommit(false);
        assertTrue(dao.create(country));
        connection.rollback();
        connection.close();
    }

    @DataProvider(name = "negativeCreate")
    public Object[] createNegativeDataForCreate() {
        return new Object[]{
                new Country(4, "Испания"),
                new Country(1, "Великобритания"),
                new Country(Integer.MIN_VALUE, "США"),
                new Country(1, null),
                new Country(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(Country country) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        dao.create(country);
        connection.close();
    }

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new Country(1, "NewName"),
                new Country(3, "AnyName"),
                new Country(2, ""),
        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(Country country) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        connection.setAutoCommit(false);
        assertTrue(dao.update(country));
        connection.rollback();
        connection.close();
    }

    @DataProvider(name = "negativeUpdate")
    public Object[] createNegativeDataForUpdate() {
        return new Object[]{
                new Country(1, null),
                new Country(3, "США"),
                new Country(2, "Испания"),
                new Country(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeUpdate", expectedExceptions = DaoException.class)
    public void testNegativeUpdate(Country country) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        dao.update(country);
        connection.close();
    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}