package by.training.dao.impl;

import by.training.dao.StudioDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Studio;
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

public class StudioDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private StudioDao dao;

    public StudioDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new StudioDaoImpl(transaction);
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
                Arrays.asList(new Studio(2, "Antena 3"),
                        new Studio(3, "BBC Two"), new Studio(1, "HBO"))
        };
    }

    @Test(dataProvider = "dataForFindAll")
    public void testFindAll(List<Studio> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Studio> actual = dao.findAll();
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "negativeDataForFindAll")
    public Object[] createNegativeDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Studio(1, "HBO"), new Studio(2, "Antena 3"),
                        new Studio(3, "BBC Two")),
                null,
                new ArrayList<Studio>(),
                new ArrayList<Integer>()
        };
    }

    @Test(dataProvider = "negativeDataForFindAll")
    public void testFindAllNegative(List<Studio> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertNotEquals(dao.findAll(), expected);
        }
    }

    @DataProvider(name = "dataForFindStudioPageByPage")
    public Object[] createPositiveDataForFindStudioPageByPage() {
        return new Object[][]{
                {1, 1, Collections.singletonList(new Studio(2, "Antena 3"))},
                {1, 2, Collections.singletonList(new Studio(3, "BBC Two"))},
                {2, 2, Collections.singletonList(new Studio(1, "HBO"))},
                {0, 2, Collections.emptyList()},
                {0, 0, Collections.emptyList()},
                {3, 1, Arrays.asList(new Studio(2, "Antena 3"), new Studio(3, "BBC Two"),
                        new Studio(1, "HBO"))}
        };
    }

    @Test(dataProvider = "dataForFindStudioPageByPage")
    public void testFindStudioPageByPage(int limit, int page, List<Studio> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Studio> actual = dao.findStudioPageByPage(page, limit);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "negativeDataForFindStudioPageByPage")
    public Object[] createNegativeDataForFindStudioPageByPage() {
        return new Object[][]{
                {-4, 6},
                {5, -7},
                {-13, -19},
                {Integer.MIN_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, -8}
        };
    }

    @Test(dataProvider = "negativeDataForFindStudioPageByPage", expectedExceptions = DaoException.class)
    public void testFindStudioPageByPageWithNegativeValue(int limit, int page) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            dao.findStudioPageByPage(page, limit);
        }
    }

    private static final int COUNT_ALL_STUDIO = 3;

    @Test
    public void testCountAllStudio() throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.countAllStudio(), COUNT_ALL_STUDIO);
        }
    }

    @DataProvider(name = "negativeDataForCountAllStudio")
    public Object[] createNegativeDataForCountAllStudio() {
        return new Object[]{
                1, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE
        };
    }

    @Test(dataProvider = "negativeDataForCountAllStudio")
    public void testCountAllStudioWithNegativeValue(int expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertNotEquals(dao.countAllStudio(), expected);
        }
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", new Studio(1, "HBO")},
                {"2", new Studio(2, "Antena 3")},
                {"3", new Studio(3, "BBC Two")},
                {"0", null},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, Studio expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            Studio actual = dao.findById(id);
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
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            assertFalse(dao.delete(id));
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveCreate")
    public Object[] createPositiveDataForCreate() {
        return new Object[]{
                new Studio(4, "TestName"),
                new Studio(5, "AnyName"),
                new Studio(Integer.MAX_VALUE, "MaxValue"),
                new Studio(Integer.MIN_VALUE, "MinValue")
        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(Studio expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.create(expected));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeCreate")
    public Object[] createNegativeDataForCreate() {
        return new Object[]{
                new Studio(4, "HBO"),
                new Studio(1, "BBC Two"),
                new Studio(Integer.MIN_VALUE, "HBO"),
                new Studio(1, null),
                new Studio(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(Studio expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            dao.create(expected);
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new Studio(1, "NewName"),
                new Studio(3, "AnyName"),
                new Studio(2, ""),
        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(Studio expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.update(expected));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeUpdate")
    public Object[] createNegativeDataForUpdate() {
        return new Object[]{
                new Studio(1, null),
                new Studio(3, "HBO"),
                new Studio(2, "BBC Two"),
                new Studio(1, "33char33char33char33char33char33c")
        };
    }

    @Test(dataProvider = "negativeUpdate", expectedExceptions = DaoException.class)
    public void testNegativeUpdate(Studio expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.update(expected);
            connection.rollback();
        }
    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}