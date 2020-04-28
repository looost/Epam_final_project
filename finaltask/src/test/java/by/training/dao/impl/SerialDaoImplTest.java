package by.training.dao.impl;

import by.training.dao.SerialDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.pool.ConnectionPool;
import by.training.model.Country;
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
import java.util.*;

import static by.training.utils.ConstantName.DEBUG_LOGGER;
import static by.training.utils.ConstantName.PATH_TO_PROPERTY_FILE;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class SerialDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    private static final Serial SERIAL_WITH_ID_1 = new Serial.Builder()
            .withId(1)
            .withName("Мир Дикого Запада")
            .withDescription("В футуристическом парке развлечений «Мир Дикого Запада» специально сконструированные андроиды выполняют любые прихоти посетителей,\n" +
                    "чтобы те чувствовали безнаказанность и полную свободу действий.")
            .withLogo("img/wworld.jpg")
            .withFullLogo("img/ww_full.jpg")
            .withReleaseDate(Date.valueOf(LocalDate.of(2016, 10, 2)))
            .withCountLike(0)
            .withCountry(new Country(3))
            .withStudio(new Studio(1))
            .withGenres(Collections.emptyList())
            .withComments(Collections.emptyList())
            .build();

    private static final Serial SERIAL_WITH_ID_2 = new Serial.Builder()
            .withId(2)
            .withName("Бумажный домик")
            .withDescription("История о преступниках, решивших ограбить Королевский монетный двор Испании и украсть 2,4 млрд евро.")
            .withLogo("img/papel.jpg")
            .withFullLogo("img/lcdp_full.jpg")
            .withReleaseDate(Date.valueOf(LocalDate.of(2017, 5, 2)))
            .withCountLike(0)
            .withCountry(new Country(3))
            .withStudio(new Studio(2))
            .withGenres(Collections.emptyList())
            .withComments(Collections.emptyList())
            .build();

    private static final Serial SERIAL_WITH_ID_3 = new Serial.Builder()
            .withId(3)
            .withName("Острые козырьки")
            .withDescription("Британский сериал о криминальном мире Бирмингема 20-х годов прошлого века, в котором многолюдная семья Шелби стала одной из самых жестоких и влиятельных гангстерских банд послевоенного времени.\n" +
                    "       Фирменным знаком группировки, промышлявшей грабежами и азартными играми, стали зашитые в козырьки лезвия.")
            .withLogo("img/breb.jpg")
            .withFullLogo("img/pb_full.jpg")
            .withReleaseDate(Date.valueOf(LocalDate.of(2013, 9, 12)))
            .withCountLike(0)
            .withCountry(new Country(2))
            .withStudio(new Studio(3))
            .withGenres(Collections.emptyList())
            .withComments(Collections.emptyList())
            .build();

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
    public Object[][] createNegativeDataForFindSerialPageByPage() {
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

//    @Test(dataProvider = "positiveDataForFindSerialBySearchForm")
//    public void testFindSerialBySearchForm(SearchForm searchForm, List<Serial> expected) throws DaoException, SQLException {
//        try (Connection connection = connectionPool.getConnection()) {
//            when(transaction.getConnection()).thenReturn(connection);
//            assertEquals(dao.findSerialBySearchForm(searchForm), expected);
//        }
//    }

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
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(2))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MIN_VALUE)
                        .withName("TEST23423")
                        .withDescription("1")
                        .withLogo(null)
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MIN_VALUE)
                        .withName("")
                        .withDescription("1")
                        .withLogo(null)
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MIN_VALUE)
                        .withName("TEST23423")
                        .withDescription("")
                        .withLogo(null)
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MIN_VALUE)
                        .withName("TEST23423")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo(null)
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(3))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo(null)
                        .withFullLogo(null)
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(3))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build()
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
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName(null)
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(2))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription(null)
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName(null)
                        .withDescription(null)
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(3))
                        .withStudio(new Studio(2))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("Мир Дикого Запада")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(2))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(Integer.MIN_VALUE))
                        .withStudio(new Studio(2))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(99))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("46char46char46char46char46char46char46char46ch")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(99))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025charr1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025charr1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025char1025cha")
                        .withLogo("logo")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(99))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo("101char101char101char101char101char101char101char101char101char101char101char101char101char101ch101ch")
                        .withFullLogo("full_logo")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(99))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("TEST")
                        .withDescription("descr")
                        .withLogo("logo")
                        .withFullLogo("101char101char101char101char101char101char101char101char101char101char101char101char101char101ch101ch")
                        .withReleaseDate(Date.valueOf(LocalDate.now()))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(99))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
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

    @DataProvider(name = "positiveUpdate")
    public Object[] createPositiveDataForUpdate() {
        return new Object[]{
                new Serial.Builder()
                        .withId(1)
                        .withName("New NAme")
                        .withDescription("New desc")
                        .withLogo("new logo")
                        .withFullLogo("new FUll logo")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(3)
                        .withName("Мир Дикого Запада2")
                        .withDescription("new DESC")
                        .withLogo("img/wworld.jpg")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2016, 10, 2)))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build()
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
                new Serial.Builder()
                        .withId(Integer.MIN_VALUE)
                        .withName("New NAme")
                        .withDescription("New desc")
                        .withLogo("new logo")
                        .withFullLogo("new FUll logo")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(Integer.MAX_VALUE)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo("img/wworld.jpg")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2016, 10, 2)))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(0)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo("img/wworld.jpg")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2016, 10, 2)))
                        .withCountLike(0)
                        .withCountry(new Country(1))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build()
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
                new Serial.Builder()
                        .withId(1)
                        .withName(null)
                        .withDescription("New desc")
                        .withLogo("img/wworld.jpg")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(3)
                        .withName("Мир Дикого Запада")
                        .withDescription(null)
                        .withLogo("img/wworld.jpg")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(3)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo(null)
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(3))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(1)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo("new logo")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio())
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(3)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo("new logo")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(Integer.MAX_VALUE))
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(1)
                        .withName("New NAme")
                        .withDescription("new DESC")
                        .withLogo("new logo")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country(2))
                        .withStudio(new Studio(Integer.MIN_VALUE))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build(),
                new Serial.Builder()
                        .withId(3)
                        .withName("Мир Дикого Запада")
                        .withDescription("new DESC")
                        .withLogo("new logo")
                        .withFullLogo("img/ww_full.jpg")
                        .withReleaseDate(Date.valueOf(LocalDate.of(2000, 12, 31)))
                        .withCountLike(0)
                        .withCountry(new Country())
                        .withStudio(new Studio(1))
                        .withGenres(Collections.emptyList())
                        .withComments(Collections.emptyList())
                        .build()
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

//    @Test(dataProvider = "createPositiveFindSerialsThatIWatch")
//    public void testFindSerialsThatIWatch(String userId, List<Serial> expected) throws DaoException, SQLException {
//        try (Connection connection = connectionPool.getConnection()) {
//            when(transaction.getConnection()).thenReturn(connection);
//            connection.setAutoCommit(false);
//            assertEquals(dao.findSerialsThatIWatch(userId), expected);
//            connection.rollback();
//        }
//    }

    @AfterTest
    public void destroyConnectionPool() {
        connectionPool.destroy();
    }
}