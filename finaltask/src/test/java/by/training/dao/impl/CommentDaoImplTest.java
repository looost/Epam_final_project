package by.training.dao.impl;

import by.training.dao.CommentDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
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
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static by.training.utils.ConstantName.DEBUG_LOGGER;
import static by.training.utils.ConstantName.PATH_TO_PROPERTY_FILE;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CommentDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    private static final Comment COMMENT_WITH_ID_1 = new Comment(1, new User(1),
            new Serial(1),
            "Коммннтарий1",
            LocalDateTime.of(2020, Month.APRIL, 22, 23, 50, 43));
    private static final Comment COMMENT_WITH_ID_2 = new Comment(2, new User(3),
            new Serial(2),
            "Коммннтарий2",
            LocalDateTime.of(2020, Month.APRIL, 22, 23, 50, 43));
    private static final Comment COMMENT_WITH_ID_3 = new Comment(3, new User(2),
            new Serial(1),
            "Коммннтарий3",
            LocalDateTime.of(2020, Month.APRIL, 22, 23, 50, 43));

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private CommentDao dao;

    public CommentDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new CommentDaoImpl(transaction);
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

    @DataProvider(name = "positiveDataForFindAllCommentForSerial")
    public Object[][] createPositiveDataForFindAllCommentForSerial() {
        return new Object[][]{
                {"1", Arrays.asList(COMMENT_WITH_ID_1, COMMENT_WITH_ID_3)},
                {"2", Collections.singletonList(COMMENT_WITH_ID_2)},
                {"3", Collections.emptyList()},

        };
    }

    @Test(dataProvider = "positiveDataForFindAllCommentForSerial")
    public void testFindAllCommentForSerial(String serialId, List<Comment> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Comment> actual = dao.findAllCommentForSerial(serialId);
            assertEquals(actual, expected);
        }
    }

    @DataProvider(name = "negativeDataForFindAllCommentForSerial")
    public Object[][] createNegativeDataForFindAllCommentForSerial() {
        return new Object[][]{
                {"1", Arrays.asList(COMMENT_WITH_ID_2, COMMENT_WITH_ID_3)},
                {"2", Collections.singletonList(COMMENT_WITH_ID_1)}

        };
    }

    @Test(dataProvider = "negativeDataForFindAllCommentForSerial")
    public void negativeTestFindAllCommentForSerial(String serialId, List<Comment> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            List<Comment> actual = dao.findAllCommentForSerial(serialId);
            assertNotEquals(actual, expected);
        }
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", COMMENT_WITH_ID_1},
                {"2", COMMENT_WITH_ID_2},
                {"3", COMMENT_WITH_ID_3},
                {"0", null},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, Comment expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            Comment actual = dao.findById(id);
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
                new Comment(2, new User(3),
                        new Serial(2),
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(Integer.MAX_VALUE, new User(2),
                        new Serial(2),
                        "TEdsfsdfsdfST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(Integer.MIN_VALUE, new User(1),
                        new Serial(2),
                        "",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(2, new User(3),
                        new Serial(2),
                        "TESeeeeT",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),

        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(Comment expected) throws DaoException, SQLException {
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
                new Comment(2, new User(Integer.MIN_VALUE),
                        new Serial(2),
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(2, new User(10),
                        new Serial(2),
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(2, new User(3),
                        new Serial(Integer.MAX_VALUE),
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(1, new User(1),
                        new Serial(1),
                        "513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char51",
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(Comment expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            dao.create(expected);
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeCreateWithNullPointerException")
    public Object[] createNegativeDataForCreateWithNullPointerException() {
        return new Object[]{
                new Comment(2, null,
                        new Serial(2),
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(8, new User(10),
                        null,
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12)),
                new Comment(66, null,
                        null,
                        "TEST",
                        LocalDateTime.of(2021, Month.APRIL, 22, 15, 8, 12))

        };
    }

    @Test(dataProvider = "negativeCreateWithNullPointerException", expectedExceptions = NullPointerException.class)
    public void testNegativeCreateWithNullPointerException(Comment expected) throws DaoException, SQLException {
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
                new Comment(1, new User(1),
                        new Serial(1),
                        "New comment",
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
                new Comment(1, new User(Integer.MIN_VALUE),
                        new Serial(1),
                        "",
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
                new Comment(2, new User(1),
                        new Serial(1),
                        "aasas",
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12))

        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(Comment expected) throws DaoException, SQLException {
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
                new Comment(0, new User(1),
                        new Serial(1),
                        null,
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
                new Comment(Integer.MIN_VALUE, new User(1),
                        new Serial(1),
                        null,
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
                new Comment(Integer.MAX_VALUE, new User(1),
                        new Serial(1),
                        null,
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
        };
    }

    @Test(dataProvider = "negativeUpdate")
    public void testNegativeUpdate(Comment expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertFalse(dao.update(expected));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeUpdateWithException")
    public Object[] createNegativeDataForUpdateWithException() {
        return new Object[]{
                new Comment(1, new User(1),
                        new Serial(1),
                        null,
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
                new Comment(1, new User(1),
                        new Serial(1),
                        "513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char513char51",
                        LocalDateTime.of(2020, Month.APRIL, 22, 15, 8, 12)),
        };
    }

    @Test(dataProvider = "negativeUpdateWithException", expectedExceptions = DaoException.class)
    public void testNegativeUpdateWithException(Comment expected) throws DaoException, SQLException {
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