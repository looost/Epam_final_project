package by.training.dao.impl;

import by.training.dao.Transaction;
import by.training.dao.UserDao;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.User;
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
import java.util.Properties;

import static by.training.utils.ConstantName.DEBUG_LOGGER;
import static by.training.utils.ConstantName.PATH_TO_PROPERTY_FILE;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private UserDao dao;

    public UserDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new UserDaoImpl(transaction);
    }

    @BeforeClass
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

    @DataProvider(name = "positiveDataForFindByLogin")
    public Object[] createPositiveDataForFindByLogin() {
        return new Object[][]{
                {"admin", new User(1, "admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0)},
                {"moder", new User(2, "moder", "$2a$10$PbjWnDHPv9W2UfCArCuZUeCusxcGhH/GBu7AlRLmd/YyEKfRapC9y", "noAvatar.png", 1)},
                {"user", new User(3, "user", "$2a$10$LuRX/n8yB/R6EK7FOBVKqOoVkYDLhwkhIj9uUM.gSWoaIu61qcfEK", "noAvatar.png", 2)},
        };
    }

    @Test(dataProvider = "positiveDataForFindByLogin")
    public void testFindByLogin(String login, User expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.findByLogin(login), expected);
        }
    }

    @DataProvider(name = "negativeDataForFindByLogin")
    public Object[] createNegativeDataForFindByLogin() {
        return new Object[][]{
                {"admin", new User(1, "Admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0)},
                {"moder", new User(2, "moder", "$3a$10$PbjWnDHPv9W2UfCArCuZUeCusxcGhH/GBu7AlRLmd/YyEKfRapC9y", "noAvatar.png", 1)},
                {"user", new User(3, "user", "$2a$10$LuRX/n8yB/R6EK7FOBVKqOoVkYDLhwkhIj9uUM.gSWoaIu61qcfEK", "noAvatar.png", 0)},
        };
    }

    @Test(dataProvider = "negativeDataForFindByLogin")
    public void testFindByLogin_negative(String login, User expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertNotEquals(dao.findByLogin(login), expected);
        }
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", new User(1, "admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0)},
                {"2", new User(2, "moder", "$2a$10$PbjWnDHPv9W2UfCArCuZUeCusxcGhH/GBu7AlRLmd/YyEKfRapC9y", "noAvatar.png", 1)},
                {"3", new User(3, "user", "$2a$10$LuRX/n8yB/R6EK7FOBVKqOoVkYDLhwkhIj9uUM.gSWoaIu61qcfEK", "noAvatar.png", 2)},
                {"0", null},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, User expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            User actual = dao.findById(id);
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
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            assertFalse(dao.delete(id));
        } finally {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
        }
    }

    @DataProvider(name = "positiveCreate")
    public Object[] createPositiveDataForCreate() {
        return new Object[]{
                new User(1, "admin2", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0),
                new User(3, "Misha", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 1),
                new User(Integer.MAX_VALUE, "newModer", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 2),
                new User(12, "TEST", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", 0)
        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(User expected) throws DaoException, SQLException {
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
                new User(1, "admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", 0),
                new User(6, "moder", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0),
                new User(2, "user", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", 6),
                new User(6, null, "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvatar.png", 0),
                new User(1, "re", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq",
                        "101char101char101char101char101char101char101char101char101char101char101char101char101char101char101", 0),
                new User(7, "TEST", null, 0),
                new User(4, null, null, 0)
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(User expected) throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            dao.create(expected);
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
        }
    }

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new User(1, "admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", "noAvfgdsgfatar.png", 0),
                new User(2, "moder", "$2a$10$PbjWnDHPv9W2UfCArCuZUeCusxcGhH/GBu7AlRLmd/YyEKfRapC9y", 1),
                new User(3, "user", "$2a$10$LuRX/n8yB/R6EK7FOBVKqOoVkYDLhwkhIj9uUM.gSWoaIu61qcfEK", "noAvatar.png", 2)
        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(User expected) throws DaoException, SQLException {
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
                new User(1, null, "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq", 0),
                new User(1, null, null, 0),
        };
    }

    @Test(dataProvider = "negativeUpdate")
    public void testNegativeUpdate(User expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertFalse(dao.update(expected));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeExceptionUpdate")
    public Object[] createNegativeDataForUpdateWithException() {
        return new Object[]{
                new User(1, "admin", null, 0),
                new User(1, "admin", "61char61char61char61char61char61char61char61char61char61char6", 0),
                new User(1, "admin", "$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq",
                        "101char101char101char101char101char101char101char101char101char101char101char101char101char101char101", 0),
        };
    }

    @Test(dataProvider = "negativeExceptionUpdate", expectedExceptions = DaoException.class)
    public void testNegativeExceptionUpdate(User expected) throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.update(expected);
        } finally {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
        }
    }


    @AfterClass
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}