package by.training.dao.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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


public class GenreDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private GenreDaoImpl dao;

    public GenreDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new GenreDaoImpl(transaction);
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

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", new Genre(1, "Драма")},
                {"2", new Genre(2, "Мистика")},
                {"3", new Genre(3, "Научная фантастика")},
                {"0", null},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, Genre expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            Genre actual = dao.findById(id);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "dataForFindByName")
    public Object[][] createPositiveDataForFindByName() {
        return new Object[][]{
                {"Драма", new Genre(1, "Драма")},
                {"Мистика", new Genre(2, "Мистика")},
                {"Научная фантастика", new Genre(3, "Научная фантастика")},
                {"0", null},
                {"Экшенн", null},
                {"Драмa", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindByName")
    public void testFindByName(String name, Genre expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            Genre actual = dao.findByName(name);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "dataForFindAll")
    public Object[] createPositiveDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Genre(1, "Драма"), new Genre(2, "Мистика"),
                        new Genre(3, "Научная фантастика"))
        };
    }

    @Test(dataProvider = "dataForFindAll")
    public void testFindAll(List<Genre> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Genre> actual = dao.findAll();
            actual.sort(Comparator.comparing(Genre::getId));
            expected.sort(Comparator.comparing(Genre::getId));
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "negativeDataForFindAll")
    public Object[] createNegativeDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Genre(1, "Драма"), new Genre(2, "Мистика"),
                        new Genre(3, "Научная фантастика"), new Genre(4, "Криминал")),
                null,
                new ArrayList<Genre>(),
                new ArrayList<Integer>()
        };
    }

    @Test(dataProvider = "negativeDataForFindAll")
    public void testFindAllNegative(List<Genre> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertNotEquals(dao.findAll(), expected);
        }
    }

    @DataProvider(name = "dataForFindGenrePageByPage")
    public Object[] createPositiveDataForFindGenrePageByPage() {
        return new Object[][]{
                {1, 1, Collections.singletonList(new Genre(1, "Драма"))},
                {1, 2, Collections.singletonList(new Genre(2, "Мистика"))},
                {2, 2, Collections.singletonList(new Genre(3, "Научная фантастика"))},
                {0, 2, Collections.emptyList()},
                {0, 0, Collections.emptyList()},
                {3, 1, Arrays.asList(new Genre(1, "Драма"),
                        new Genre(2, "Мистика"), new Genre(3, "Научная фантастика"))}
        };
    }

    @Test(dataProvider = "dataForFindGenrePageByPage")
    public void testFindGenrePageByPage(int limit, int page, List<Genre> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Genre> actual = dao.findGenrePageByPage(page, limit);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "negativeDataForFindGenrePageByPage")
    public Object[] createNegativeDataForFindGenrePageByPage() {
        return new Object[][]{
                {-1, 1},
                {2, -12},
                {-3, -1},
                {Integer.MIN_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, -2}
        };
    }

    @Test(dataProvider = "negativeDataForFindGenrePageByPage", expectedExceptions = DaoException.class)
    public void testFindGenrePageByPageWithNegativeValue(int limit, int page) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            dao.findGenrePageByPage(page, limit);
        }
    }

    private static final int COUNT_ALL_GENRE = 3;

    @Test
    public void testCountAllGenres() throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.countAllGenres(), COUNT_ALL_GENRE);
        }
    }

    @DataProvider(name = "negativeDataForCountAllGenres")
    public Object[] createNegativeDataForCountAllGenres() {
        return new Object[]{
                1, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE
        };
    }

    @Test(dataProvider = "negativeDataForCountAllGenres")
    public void testCountAllGenresWithNegativeValue(int expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertNotEquals(dao.countAllGenres(), expected);
        }
    }

    @DataProvider(name = "positiveFindGenreBySerialId")
    public Object[] createDataForFindGenreBySerialId() {
        return new Object[][]{
                {"1", Arrays.asList(new Genre(1, "Драма"), new Genre(2, "Мистика"))},
                {"2", Arrays.asList(new Genre(1, "Драма"), new Genre(3, "Научная фантастика"))},
                {"3", Collections.singletonList(new Genre(2, "Мистика"))},
                {"4", Collections.emptyList()},
                {"0", Collections.emptyList()},
                {String.valueOf(Integer.MIN_VALUE), Collections.emptyList()},
                {String.valueOf(Integer.MAX_VALUE), Collections.emptyList()},
        };
    }

    @Test(dataProvider = "positiveFindGenreBySerialId")
    public void testFindGenreBySerialId(String serialId, List<Genre> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Genre> actual = dao.findGenreBySerialId(serialId);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "positiveDelete")
    public Object[] createDataForDelete() {
        return new Object[]{
                "1", "2", "3"
        };
    }

    @Test(dataProvider = "positiveDelete")
    public void testDelete(String id) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.delete(id));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeDelete")
    public Object[] createNegativeDataForDelete() {
        return new Object[]{
                "4", null, "4.5", ""
        };
    }

    @Test(dataProvider = "negativeDelete")
    public void testNegativeDelete(String id) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertFalse(dao.delete(id));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeDeleteWithException")
    public Object[] createNegativeDataForDeleteWithException() {
        return new Object[]{
                "String", "1f", "qq"
        };
    }

    @Test(dataProvider = "negativeDeleteWithException", expectedExceptions = DaoException.class)
    public void testNegativeDeleteWithException(String id) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertFalse(dao.delete(id));
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveCreate")
    public Object[] createPositiveDataForCreate() {
        return new Object[]{
                new Genre(4, "TestName"),
                new Genre(5, "AnyName"),
                new Genre(Integer.MAX_VALUE, "MaxValue"),
                new Genre(Integer.MIN_VALUE, "MinValue")
        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(Genre genre) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.create(genre));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeCreate")
    public Object[] createNegativeDataForCreate() {
        return new Object[]{
                new Genre(4, "Драма"),
                new Genre(1, "Научная фантастика"),
                new Genre(Integer.MIN_VALUE, "Мистика"),
                new Genre(1, null),
                new Genre(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(Genre genre) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.create(genre);
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new Genre(1, "NewName"),
                new Genre(3, "AnyName"),
                new Genre(2, ""),
        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(Genre genre) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.update(genre));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeUpdate")
    public Object[] createNegativeDataForUpdate() {
        return new Object[]{
                new Genre(1, null),
                new Genre(3, "Драма"),
                new Genre(2, "Научная фантастика"),
                new Genre(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeUpdate", expectedExceptions = DaoException.class)
    public void testNegativeUpdate(Genre genre) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.update(genre);
            connection.rollback();
        }
    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}