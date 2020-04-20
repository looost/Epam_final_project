package by.training.dao.impl;

import by.training.dao.SerialDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.model.form.SearchForm;
import by.training.model.form.SearchQuery;
import by.training.service.validation.Validation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerialDaoImpl implements SerialDao {

    private Transaction transaction;

    private static final ResultSetHandler<Serial> SERIAL_RESULT_SET_HANDLER = new ResultSetHandler<Serial>() {
        @Override
        public Serial handle(ResultSet rs) throws DaoException {
            Serial serial = new Serial();
            try {
                serial.setId(rs.getInt("id"));
                serial.setName(rs.getString("name"));
                serial.setDescription(rs.getString("description"));
                serial.setLogo(rs.getString("logo"));
                serial.setFullLogo(rs.getString("full_logo"));
                serial.setReleaseDate(rs.getDate("release_date"));
                serial.setCountLike(rs.getInt("count_like"));
                Country country = new Country();
                country.setId(rs.getInt("country_id"));
                serial.setCountry(country);
                Studio studio = new Studio();
                studio.setId(rs.getInt("studio_id"));
                serial.setStudio(studio);
                serial.setGenres(new ArrayList<>());
                serial.setComments(new ArrayList<>());
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return serial;
        }
    };

    public SerialDaoImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_SERIAL_BY_NAME = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s WHERE s.name = ?";
    @Override
    public Serial findSerialByName(String name) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIAL_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), name);
    }

    private static final String FIND_ALL_SERIAL = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s";
    @Override
    public List<Serial> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER));
    }

    private static final String FIND_ALL_SERIAL_2 = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s LIMIT ? OFFSET ?";
    @Override
    public List<Serial> findAllSerial2(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_SERIAL_2,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String LATEST_SERIAL = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s ORDER BY s.id DESC LIMIT ?";

    @Override
    public List<Serial> latestSerial(int limit) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), LATEST_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit);
    }

    private static final String COUNT_ALL_SERIAL = "SELECT COUNT(*) FROM serial";
    @Override
    public int countAllSerials() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_SERIAL,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    @Override
    public List<Serial> findSerialBySearchForm(SearchForm searchForm) throws DaoException {
        SearchQuery query = buildSearchQuery(searchForm);
        return JDBCUtil.select(transaction.getConnection(), query.getSql().toString(),
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), query.getParams().toArray());
    }

    private static final String FIND_SERIAL_BY_SEARCH_FORM_2 = "SELECT DISTINCT s.id, s.* FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE (name like ? or description like ?)";
    private SearchQuery buildSearchQuery(SearchForm searchForm) {
        List<String> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(FIND_SERIAL_BY_SEARCH_FORM_2);
        param.add("%" + searchForm.getQuery() + "%");
        param.add("%" + searchForm.getQuery() + "%");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getGenres(), "sg.genre_id = ?");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getCountry(), "s.country_id = ?");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getStudio(), "s.studio_id = ?");
        return new SearchQuery(sql, param);
    }

    private static final String FIND_SERIAL_BY_GENRE = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE sg.genre_id = ?";
    @Override
    public List<Serial> findSerialByGenre(String genreId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIAL_BY_GENRE,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), genreId);
    }

    private static final String FIND_SERIAL_BY_ID = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s WHERE s.id = ?";
    @Override
    public Serial findById(String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIAL_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_SERIAL_BY_ID = "DELETE FROM serial WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_SERIAL_BY_ID, id);
    }

    private static final String CREATE_SERIAL = "INSERT INTO serial VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";
    @Override
    public boolean create(Serial entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_SERIAL,
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(), entity.getReleaseDate(),
                entity.getCountry().getId(), entity.getStudio().getId());
    }

    @Override
    public int createAndReturnIndex(Serial serial) throws DaoException {
        return JDBCUtil.executeAndReturnIndex(transaction.getConnection(), CREATE_SERIAL,
                serial.getName(), serial.getDescription(), serial.getLogo(), serial.getFullLogo(), serial.getReleaseDate(),
                serial.getCountry().getId(), serial.getStudio().getId());
    }

    private static final String SERIAL_GENRE_VALUES = "INSERT INTO serial_genre VALUES (?, ?)";
    @Override
    public boolean createSerialGenre(int serialId, List<Genre> genres) throws DaoException {
        return JDBCUtil
                .executeBatch(transaction.getConnection(), SERIAL_GENRE_VALUES, refactorToParamList(serialId, genres));
    }

    private List<Object[]> refactorToParamList(int serialId, List<Genre> genres) {
        List<Object[]> paramList = new ArrayList<>();
        for (Genre g : genres
        ) {
            paramList.add(new Object[]{serialId, g.getId()});
        }
        return paramList;
    }

    private static final String UPDATE_SERIAL = "UPDATE serial SET name = ?, description = ?," +
            "  country_id = ?, studio_id = ? WHERE id = ?";
    @Override
    public boolean update(Serial entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_SERIAL,
                entity.getName(), entity.getDescription(),
                entity.getCountry().getId(), entity.getStudio().getId(), entity.getId());
    }

    private static final String WATCH_SERIAL = "INSERT INTO viewed VALUES (?, ?)";
    @Override
    public boolean toWatchSerial(String userId, String serialId) throws DaoException {
        if (Validation.serialIsWatch(transaction, serialId, userId)) {
            return JDBCUtil.execute(transaction.getConnection(), WATCH_SERIAL, userId, serialId);
        }
        return false;
    }

    private static final String STOP_WATCH_SERIAL = "DELETE FROM viewed WHERE (user_id = ? and serial_id = ?)";
    @Override
    public boolean stopWatchSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), STOP_WATCH_SERIAL, userId, serialId);
    }

    private static final String SERIAL_IS_WATCH_STATUS = "SELECT s.id, s.name, s.description, s.logo, s.full_logo, " +
            "s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s JOIN viewed v on s.id = v.serial_id WHERE v.user_id = ? and v.serial_id = ?";

    @Override
    public Serial userWatchThisSerial(String serialId, String userId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), SERIAL_IS_WATCH_STATUS,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), userId, serialId);
    }

    private static final String FIND_SERIALS_THAT_I_WATCH = "SELECT s.id, s.name, s.description, s.logo, s.full_logo, " +
            "s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s JOIN viewed v on s.id = v.serial_id WHERE v.user_id = ?";
    @Override
    public List<Serial> findSerialsThatIWatch(String userId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIALS_THAT_I_WATCH,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), userId);
    }
}
