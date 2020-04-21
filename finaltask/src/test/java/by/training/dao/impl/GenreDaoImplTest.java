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
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class GenreDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    private static final String PATH_TO_PROPERTY_FILE = "src\\test\\resources\\pool.properties";
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
        // BDDMockito.given(transaction.getConnection()).willReturn(getConnection());
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        //when(transaction.getConnection()).thenReturn(getFakeConnection());
//        Genre genre = dao.findById("1");
//        assertEquals(genre, new Genre(1, "Драма"));
//        connection.close();

        Genre actual = dao.findById(id);
        assertEquals(actual, expected);
        connection.close();
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
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        Genre actual = dao.findByName(name);
        assertEquals(actual, expected);
        connection.close();
    }

    @DataProvider(name = "dataForFindAll")
    public Object[] createPositiveDataForFindAll() {
        return new Object[]{
                Arrays.asList(new Genre(1, "Драма"), new Genre(2, "Мистика"),
                        new Genre(3, "Научная фантастика"), new Genre(4, "Криминал"))
        };
    }

    @Test(dataProvider = "dataForFindAll")
    public void testFindAll(List<Genre> expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        List<Genre> actual = dao.findAll();
        actual.sort(Comparator.comparing(Genre::getId));
        expected.sort(Comparator.comparing(Genre::getId));
        assertEquals(actual, expected);
        connection.close();
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
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        assertNotEquals(dao.findAll(), expected);
        connection.close();
    }

    @DataProvider(name = "dataForFindGenrePageByPage")
    public Object[] createPositiveDataForFindGenrePageByPage() {
        return new Object[][]{
                {1, 1, Collections.singletonList(new Genre(4, "Криминал"))},
                {1, 2, Collections.singletonList(new Genre(2, "Мистика"))},
                {2, 2, Arrays.asList(new Genre(2, "Мистика"), new Genre(3, "Научная фантастика"))},
                {0, 2, Collections.emptyList()},
                {0, 0, Collections.emptyList()},
                {3, 1, Arrays.asList(new Genre(4, "Криминал"),
                        new Genre(2, "Мистика"), new Genre(3, "Научная фантастика"))},
        };
    }

    @Test(dataProvider = "dataForFindGenrePageByPage")
    public void testFindGenrePageByPage(int limit, int page, List<Genre> expected) throws DaoException, SQLException {
        Connection connection = connectionPool.getConnection();
        when(transaction.getConnection()).thenReturn(connection);
        List<Genre> actual = dao.findGenrePageByPage(page, limit);
        logger.debug(actual);
        assertEquals(actual, expected);
        connection.close();
    }

//
//    @Test
//    public void testCountAllGenres() {
//    }
//
//    @Test
//    public void testFindGenreBySerialId() {
//    }
//
//    @Test
//    public void testTestFindById() {
//    }
//
//    @Test
//    public void testDelete() {
//    }
//
//    @Test
//    public void testCreate() {
//    }
//
//    @Test
//    public void testUpdate() {
//    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}