package by.training.dao.impl;

import by.training.dao.SerialDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.model.form.SearchForm;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static by.training.utils.ConstantName.DEBUG_LOGGER;
import static by.training.utils.ConstantName.PATH_TO_PROPERTY_FILE;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class SerialDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    private static final Serial SERIAL_WITH_ID_1 = new Serial(1, "Мир Дикого Запада", "В футуристическом парке развлечений «Мир Дикого Запада» специально сконструированные андроиды выполняют любые прихоти посетителей,\n" +
            "чтобы те чувствовали безнаказанность и полную свободу действий.", "img/wworld.jpg", "img/ww_full.jpg", Date.valueOf(LocalDate.of(2016, 10, 2)),
            0, new Country(1), new Studio(1), Collections.emptyList(), Collections.emptyList());
    private static final Serial SERIAL_WITH_ID_2 = new Serial(2, "Бумажный домик", "История о преступниках, решивших ограбить Королевский монетный двор Испании и украсть 2,4 млрд евро.",
            "img/papel.jpg", "img/lcdp_full.jpg", Date.valueOf(LocalDate.of(2017, 5, 2)),
            0, new Country(3), new Studio(2), Collections.emptyList(), Collections.emptyList());
    private static final Serial SERIAL_WITH_ID_3 = new Serial(3, "Острые козырьки", "Британский сериал о криминальном мире Бирмингема 20-х годов прошлого века, в котором многолюдная семья Шелби стала одной из самых жестоких и влиятельных гангстерских банд послевоенного времени.\n" +
            "       Фирменным знаком группировки, промышлявшей грабежами и азартными играми, стали зашитые в козырьки лезвия.",
            "img/breb.jpg", "img/pb_full.jpg", Date.valueOf(LocalDate.of(2013, 9, 12)),
            0, new Country(2), new Studio(3), Collections.emptyList(), Collections.emptyList());

    @Mock
    private Transaction transaction;
    private ConnectionPool connectionPool;
    private SerialDao dao;

    public SerialDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new SerialDaoImpl(transaction);
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

    @DataProvider(name = "positiveDataForFindAllSerialPageByPage")
    public Object[] createPositiveDataForFindAllSerialPageByPage() {
        return new Object[][]{
                {1, 1, Collections.singletonList(SERIAL_WITH_ID_1)},
                {1, 2, Collections.singletonList(SERIAL_WITH_ID_2)},
                {2, 2, Collections.singletonList(SERIAL_WITH_ID_3)},
                {3, 1, Arrays.asList(SERIAL_WITH_ID_1, SERIAL_WITH_ID_2, SERIAL_WITH_ID_3)},
                {0, 2, Collections.emptyList()},
                {0, 0, Collections.emptyList()}
        };
    }

    @Test(dataProvider = "positiveDataForFindAllSerialPageByPage")
    public void testPositiveFindAllSerialPageByPage(int limit, int page, List<Serial> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.findSerialPageByPage(page, limit), expected);
        }
    }

    @DataProvider(name = "negativeDataForFindSerialPageByPage")
    public Object[] createNegativeDataForFindSerialPageByPage() {
        return new Object[][]{
                {-4, 6},
                {5, -7},
                {-13, -19},
                {Integer.MIN_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, -8}
        };
    }

    @Test(dataProvider = "negativeDataForFindSerialPageByPage", expectedExceptions = DaoException.class)
    public void testFindSerialPageByPageWithNegativeValue(int limit, int page) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            dao.findSerialPageByPage(page, limit);
        }
    }

    @DataProvider(name = "positiveDataForLatestSerial")
    public Object[] createPositiveDataForLatestSerial() {
        return new Object[][]{
                {1, Collections.singletonList(SERIAL_WITH_ID_3)},
                {2, Arrays.asList(SERIAL_WITH_ID_3, SERIAL_WITH_ID_2)},
                {3, Arrays.asList(SERIAL_WITH_ID_3, SERIAL_WITH_ID_2, SERIAL_WITH_ID_1)},
                {0, Collections.emptyList()}
        };
    }

    @Test(dataProvider = "positiveDataForLatestSerial")
    public void testLatestSerial(int limit, List<Serial> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.latestSerial(limit), expected);
        }
    }

    @DataProvider(name = "negativeDataForLatestSerial")
    public Object[] createNegativeDataForLatestSerial() {
        return new Object[][]{
                {-4},
                {-13},
                {Integer.MIN_VALUE}
        };
    }

    @Test(dataProvider = "negativeDataForLatestSerial", expectedExceptions = DaoException.class)
    public void testFindLatestSerialWithNegativeValue(int limit) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            dao.latestSerial(limit);
        }
    }

    private static final int COUNT_ALL_SERIAL = 3;

    @Test
    public void testCountAllSerials() throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.countAllSerials(), COUNT_ALL_SERIAL);
        }
    }

    @DataProvider(name = "positiveDataForFindSerialBySearchForm")
    public Object[][] createPositiveDataForFindSerialBySearchForm() {
        return new Object[][]{
                {new SearchForm("Острые", null, null, null),
                        Collections.singletonList(SERIAL_WITH_ID_3)},
                {new SearchForm("TEST", null, null, null),
                        Collections.emptyList()},
                {new SearchForm("", null, null, new String[]{"3"}),
                        Collections.singletonList(SERIAL_WITH_ID_3)},
                {new SearchForm("", null, null, null),
                        Arrays.asList(SERIAL_WITH_ID_1, SERIAL_WITH_ID_2, SERIAL_WITH_ID_3)},
                {new SearchForm("", null, new String[]{"2"}, new String[]{"3"}),
                        Collections.singletonList(SERIAL_WITH_ID_3)},
                {new SearchForm("", new String[]{"1"}, null, null),
                        Arrays.asList(SERIAL_WITH_ID_1, SERIAL_WITH_ID_2)},
                {new SearchForm("", new String[]{"1", "2"}, null, null),
                        Arrays.asList(SERIAL_WITH_ID_1, SERIAL_WITH_ID_2, SERIAL_WITH_ID_3)},
                {new SearchForm(null, null, null, null), Collections.emptyList()}
        };
    }

    @Test(dataProvider = "positiveDataForFindSerialBySearchForm")
    public void testFindSerialBySearchForm(SearchForm searchForm, List<Serial> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.findSerialBySearchForm(searchForm), expected);
        }
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] createPositiveDataForFindById() {
        return new Object[][]{
                {"1", SERIAL_WITH_ID_1},
                {"2", SERIAL_WITH_ID_2},
                {"3", SERIAL_WITH_ID_3},
                {"10", null},
                {null, null},
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testFindById(String id, Serial expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            assertEquals(dao.findById(id), expected);
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
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "TEST23423", "1", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "", "1", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "TEST23423", "", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", null, Date.valueOf(LocalDate.now()),
                        0, new Country(3), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", null, null, Date.valueOf(LocalDate.now()),
                        0, new Country(3), new Studio(3),
                        Collections.emptyList(), Collections.emptyList())
        };
    }

    @Test(dataProvider = "positiveCreate")
    public void testCreate(Serial expected) throws DaoException, SQLException {
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
                new Serial(Integer.MAX_VALUE, null, "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", null, "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, null, null, "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(3), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "Мир Дикого Запада", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(Integer.MIN_VALUE), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(99),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "46char46char46char46char46char46char46char46ch", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025charr1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025charr1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025cha", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "101char101char101char101char101char101char101char101char101char101char101char101char101char101ch101ch", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", "101char101char101char101char101char101char101char101char101char101char101char101char101char101ch101ch", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                SERIAL_WITH_ID_1
        };
    }

    @Test(dataProvider = "negativeCreate", expectedExceptions = DaoException.class)
    public void testNegativeCreate(Serial expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            dao.create(expected);
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveCreateAndReturnIndex")
    public Object[] createPositiveDataForCreateAndReturnIndex() {
        return new Object[]{
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(1), new Studio(2),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "TEST23423", "1", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "", "1", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MIN_VALUE, "TEST23423", "", null, "full_logo", Date.valueOf(LocalDate.now()),
                        0, new Country(2), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", "logo", null, Date.valueOf(LocalDate.now()),
                        0, new Country(3), new Studio(1),
                        Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "TEST", "descr", null, null, Date.valueOf(LocalDate.now()),
                        0, new Country(3), new Studio(3),
                        Collections.emptyList(), Collections.emptyList())
        };
    }

    @Test(dataProvider = "positiveCreateAndReturnIndex")
    public void testCreateAndReturnIndex(Serial serial) throws DaoException, SQLException {

    }

    @DataProvider(name = "positiveCreateSerialGenre")
    public Object[] createPositiveDataForCreateSerialGenre() {
        return new Object[][]{
                {1, Collections.singletonList(
                        new Genre(3)
                )},
                {2, Collections.singletonList(
                        new Genre(2)
                )},
                {3, Arrays.asList(
                        new Genre(3), new Genre(1)
                )},
                {4, Collections.emptyList()}
        };
    }

    @Test(dataProvider = "positiveCreateSerialGenre")
    public void testPositiveCreateSerialGenre(int serialId, List<Genre> genres) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            assertTrue(dao.createSerialGenre(serialId, genres));
            connection.rollback();
        }
    }

    @DataProvider(name = "negativeCreateSerialGenre")
    public Object[] createNegativeDataForCreateSerialGenre() {
        return new Object[][]{
                {4, Arrays.asList(
                        new Genre(1),
                        new Genre(2),
                        new Genre(3)
                )},
                {Integer.MIN_VALUE, Arrays.asList(
                        new Genre(2),
                        new Genre(3)
                )},
                {0, Collections.singletonList(
                        new Genre(3)
                )},
                {2, Collections.singletonList(
                        new Genre(-2))},
                {1, Collections.singletonList(
                        new Genre()
                )},
                {2, Collections.singletonList(
                        new Genre(Integer.MAX_VALUE)
                )},
        };
    }

    @Test(dataProvider = "negativeCreateSerialGenre", expectedExceptions = DaoException.class)
    public void testNegativeCreateSerialGenre(int serialId, List<Genre> genres) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            when(transaction.getConnection()).thenReturn(connection);
            dao.createSerialGenre(serialId, genres);
            connection.rollback();
        }
    }

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new Serial(1, "New NAme", "New desc", "new logo", "new FUll logo",
                        Date.valueOf(LocalDate.of(2000, 12, 31)),
                        0, new Country(2), new Studio(3), Collections.emptyList(), Collections.emptyList()),
                new Serial(3, "Мир Дикого Запада2", "new DESC", "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(1), new Studio(1), Collections.emptyList(), Collections.emptyList())
        };
    }

    @Test(dataProvider = "positiveUpdate")
    public void testUpdate(Serial expected) throws DaoException, SQLException {
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
                new Serial(Integer.MIN_VALUE, "New NAme", "New desc", "new logo", "new FUll logo",
                        Date.valueOf(LocalDate.of(2000, 12, 31)),
                        0, new Country(2), new Studio(3), Collections.emptyList(), Collections.emptyList()),
                new Serial(Integer.MAX_VALUE, "Мир Дикого Запада", "new DESC", "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(1), new Studio(1), Collections.emptyList(), Collections.emptyList()),
                new Serial(0, "Мир Дикого Запада", "new DESC", "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(1), new Studio(1), Collections.emptyList(), Collections.emptyList()),
        };
    }

    @Test(dataProvider = "negativeUpdate")
    public void testNegativeUpdate(Serial expected) throws DaoException, SQLException {
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
                new Serial(1, null, "New desc", "new logo", "new FUll logo",
                        Date.valueOf(LocalDate.of(2000, 12, 31)),
                        0, new Country(2), new Studio(3), Collections.emptyList(), Collections.emptyList()),
                new Serial(3, "Мир Дикого Запада", null, "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(2), new Studio(3), Collections.emptyList(), Collections.emptyList()),
                new Serial(3, "Мир Дикого Запада", "new DESC", "img/wworld.jpg", null,
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(1), new Studio(1), Collections.emptyList(), Collections.emptyList()),
                new Serial(1, "New NAme", "New desc", "new logo", "new FUll logo",
                        Date.valueOf(LocalDate.of(2000, 12, 31)),
                        0, new Country(2), new Studio(), Collections.emptyList(), Collections.emptyList()),
                new Serial(3, "Мир Дикого Запада", "new DESC", "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(Integer.MAX_VALUE), new Studio(1), Collections.emptyList(), Collections.emptyList()),
                new Serial(1, "New NAme", "New desc", "new logo", "new FUll logo",
                        Date.valueOf(LocalDate.of(2000, 12, 31)),
                        0, new Country(2), new Studio(Integer.MIN_VALUE), Collections.emptyList(), Collections.emptyList()),
                new Serial(3, "Мир Дикого Запада", "new DESC", "img/wworld.jpg", "img/ww_full.jpg",
                        Date.valueOf(LocalDate.of(2016, 10, 2)),
                        0, new Country(), new Studio(1), Collections.emptyList(), Collections.emptyList())
        };
    }

    @Test(dataProvider = "negativeUpdateWithException", expectedExceptions = DaoException.class)
    public void testNegativeUpdateWithException(Serial expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.update(expected);
            connection.rollback();
        }
    }

    @DataProvider(name = "createPositiveDateForToWatchSerial")
    public Object[] createPositiveDataForToWatchSerial() {
        return new Object[][]{
                {"1", "2"},
                {"1", "3"},
                {"2", "1"},
                {"3", "1"},
                {"3", "3"}
        };
    }

    @Test(dataProvider = "createPositiveDateForToWatchSerial")
    public void testToWatchSerial(String userId, String serialId) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.toWatchSerial(userId, serialId));
            connection.rollback();
        }
    }

    @DataProvider(name = "createNegativeDateForToWatchSerial")
    public Object[] createNegativeDataForToWatchSerial() {
        return new Object[][]{
                {"1", "1"},
                {"3", "2"},
                {"2", "3"},
                {"String", "1"},
                {"3", "String"},
                {"", ""}
        };
    }

    @Test(dataProvider = "createNegativeDateForToWatchSerial", expectedExceptions = DaoException.class)
    public void testNegativeToWatchSerial(String userId, String serialId) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.toWatchSerial(userId, serialId);
            connection.rollback();
        }
    }


    @DataProvider(name = "createPositiveDateForStopWatchSerial")
    public Object[] createPositiveDataForStopWatchSerial() {
        return new Object[][]{
                {"1", "1"},
                {"3", "2"},
                {"2", "3"}
        };
    }

    @Test(dataProvider = "createPositiveDateForStopWatchSerial")
    public void testStopWatchSerial(String userId, String serialId) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertTrue(dao.stopWatchSerial(userId, serialId));
            connection.rollback();
        }
    }

    @DataProvider(name = "createNegativeDateForStopWatchSerial")
    public Object[] createNegativeDataForStopWatchSerial() {
        return new Object[][]{
                {"1", "3"},
                {"1", "5"},
                {"2", "1"},
                {"3", "3"},
                {"", ""},
                {String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MIN_VALUE)}
        };
    }

    @Test(dataProvider = "createNegativeDateForStopWatchSerial")
    public void testNegativeStopWatchSerial(String userId, String serialId) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertFalse(dao.stopWatchSerial(userId, serialId));
            connection.rollback();
        }
    }

    @DataProvider(name = "createNegativeDateForStopWatchSerialWithException")
    public Object[] createNegativeDataForStopWatchSerialWithException() {
        return new Object[][]{
                {"String", "3"},
                {"1", "String"}
        };
    }

    @Test(dataProvider = "createNegativeDateForStopWatchSerialWithException", expectedExceptions = DaoException.class)
    public void testNegativeStopWatchSerialWithException(String userId, String serialId) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            dao.stopWatchSerial(userId, serialId);
            connection.rollback();
        }
    }

    @DataProvider(name = "createPositiveUserWatchThisSerial")
    public Object[] createPositiveDataForUserWatchThisSerial() {
        return new Object[][]{
                {"1", "1", SERIAL_WITH_ID_1},
                {"3", "2", SERIAL_WITH_ID_2},
                {"2", "3", SERIAL_WITH_ID_3},
                {String.valueOf(Integer.MIN_VALUE), "4", null},
                {"1", String.valueOf(Integer.MAX_VALUE), null}
        };
    }

    @Test(dataProvider = "createPositiveUserWatchThisSerial")
    public void testUserWatchThisSerial(String userId, String serialId, Serial expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertEquals(dao.userWatchThisSerial(serialId, userId), expected);
            connection.rollback();
        }
    }

    @DataProvider(name = "createPositiveFindSerialsThatIWatch")
    public Object[] createPositiveDataForFindSerialsThatIWatch() {
        return new Object[][]{
                {"1", Collections.singletonList(SERIAL_WITH_ID_1)},
                {"3", Collections.singletonList(SERIAL_WITH_ID_2)},
                {"2", Collections.singletonList(SERIAL_WITH_ID_3)},
                {"4", Collections.emptyList()},
                {String.valueOf(Integer.MIN_VALUE), Collections.emptyList()}
        };
    }

    @Test(dataProvider = "createPositiveFindSerialsThatIWatch")
    public void testFindSerialsThatIWatch(String userId, List<Serial> expected) throws DaoException, SQLException {
        try (Connection connection = connectionPool.getConnection()) {
            when(transaction.getConnection()).thenReturn(connection);
            connection.setAutoCommit(false);
            assertEquals(dao.findSerialsThatIWatch(userId), expected);
            connection.rollback();
        }
    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}