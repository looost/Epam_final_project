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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link SerialDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Serial}.
 *
 * @see Transaction
 * @see DaoException
 */
public class SerialDaoImpl implements SerialDao {

    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Serial} from result set.
     *
     * @see ResultSet
     */
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

    private static final String SELECT_ALL_SERIAL_FIELD = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s";

    private static final String FIND_SERIAL_BY_NAME = SELECT_ALL_SERIAL_FIELD + " WHERE s.name = ?";

    /**
     * Find serial by serial name.
     *
     * @param name the name
     * @return the {@link Serial} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Serial findSerialByName(String name) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIAL_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), name);
    }

    private static final String FIND_ALL_SERIAL = SELECT_ALL_SERIAL_FIELD;
    @Override
    public List<Serial> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER));
    }

    private static final String FIND_SERIAL_PAGE_BY_PAGE = SELECT_ALL_SERIAL_FIELD + " LIMIT ? OFFSET ?";

    /**
     * Find serial page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Serial> findSerialPageByPage(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIAL_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String FIND_MOST_LIKED_SERIAL = SELECT_ALL_SERIAL_FIELD + " ORDER BY s.count_like DESC LIMIT ? OFFSET ?";

    /**
     * Find most liked serial.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Serial> findMostLikedSerial(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_MOST_LIKED_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String LATEST_SERIAL = SELECT_ALL_SERIAL_FIELD + " ORDER BY s.id DESC LIMIT ?";

    /**
     * Latest serial.
     *
     * @param limit the limit
     * @return the list of serials and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Serial> latestSerial(int limit) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), LATEST_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit);
    }


    private static final String COUNT_ALL_SERIAL = "SELECT COUNT(*) FROM serial";

    /**
     * Count all serials.
     *
     * @return number of all serials
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllSerials() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_SERIAL,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    /**
     * Find serial by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of serials and null if no results are found
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    @Override
    public List<Serial> findSerialBySearchForm(SearchForm searchForm, int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        SearchQuery query = buildSearchQuery(searchForm, FIND_SERIAL_BY_SEARCH_FORM_2);
        query.getSql().append(" LIMIT ? OFFSET ?");
        query.getParams().add(limit);
        query.getParams().add(offset);
        return JDBCUtil.select(transaction.getConnection(), query.getSql().toString(),
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), query.getParams().toArray());
    }

    private static final String FIND_SERIAL_BY_SEARCH_FORM_2 = "SELECT DISTINCT s.id, s.* FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE (name like ? or description like ?)";

    /**
     * Build SQL query
     *
     * @param searchForm
     * @param temp
     * @return
     */
    private SearchQuery buildSearchQuery(SearchForm searchForm, String temp) {
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(temp);
        param.add("%" + searchForm.getQuery() + "%");
        param.add("%" + searchForm.getQuery() + "%");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getGenres(), "sg.genre_id = ?");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getCountry(), "s.country_id = ?");
        JDBCUtil.populateSqlAndParams(sql, param, searchForm.getStudio(), "s.studio_id = ?");
        return new SearchQuery(sql, param);
    }

    private static final String COUNT_ALL_SERIAL_BY_SEARCH_FORM = "SELECT COUNT(DISTINCT s.id) FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE (name like ? or description like ?)";

    @Override
    public int countAllSerialsBySearchForm(SearchForm searchForm) throws DaoException {
        SearchQuery query = buildSearchQuery(searchForm, COUNT_ALL_SERIAL_BY_SEARCH_FORM);
        return JDBCUtil.select(transaction.getConnection(), query.getSql().toString(),
                ResultSetHandlerFactory.getCountResultSetHandler(), query.getParams().toArray());
    }

    private static final String FIND_SERIAL_BY_ID = SELECT_ALL_SERIAL_FIELD + " WHERE s.id = ?";
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
    private static final String SERIAL_GENRE_VALUES = "INSERT INTO serial_genre VALUES (?, ?)";
    @Override
    public boolean create(Serial serial) throws DaoException {
        int serialId = JDBCUtil.executeAndReturnIndex(transaction.getConnection(), CREATE_SERIAL,
                serial.getName(), serial.getDescription(), serial.getLogo(), serial.getFullLogo(), serial.getReleaseDate(),
                serial.getCountry().getId(), serial.getStudio().getId());
        return JDBCUtil.executeBatch(transaction.getConnection(), SERIAL_GENRE_VALUES,
                        refactorToParamList(serialId, serial.getGenres()));
    }

    private List<Object[]> refactorToParamList(int serialId, List<Genre> genres) {
        List<Object[]> paramList = new ArrayList<>();
        for (Genre g : genres
        ) {
            paramList.add(new Object[]{serialId, g.getId()});
        }
        return paramList;
    }

    private static final String UPDATE_SERIAL = "UPDATE serial SET name = ?, description = ?, logo = ?, full_logo = ?," +
            " release_date = ?, country_id = ?, studio_id = ? WHERE id = ?";
    private static final String DELETE_SERIAL_GENRE = "DELETE FROM serial_genre WHERE serial_id = ?";
    @Override
    public boolean update(Serial entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_SERIAL,
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(),
                entity.getReleaseDate(), entity.getCountry().getId(), entity.getStudio().getId(), entity.getId()) &
                JDBCUtil.execute(transaction.getConnection(), DELETE_SERIAL_GENRE, entity.getId()) &
                JDBCUtil.executeBatch(transaction.getConnection(), SERIAL_GENRE_VALUES,
                                refactorToParamList(entity.getId(), entity.getGenres()));
    }

    private static final String WATCH_SERIAL = "INSERT INTO viewed VALUES (?, ?)";
    @Override
    public boolean toWatchSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), WATCH_SERIAL, userId, serialId);
    }

    private static final String STOP_WATCH_SERIAL = "DELETE FROM viewed WHERE (user_id = ? and serial_id = ?)";
    @Override
    public boolean stopWatchSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), STOP_WATCH_SERIAL, userId, serialId);
    }

    private static final String SERIAL_IS_WATCH_STATUS = SELECT_ALL_SERIAL_FIELD + " JOIN viewed v on s.id = v.serial_id WHERE v.user_id = ? and v.serial_id = ?";
    @Override
    public boolean userWatchThisSerial(String serialId, String userId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), SERIAL_IS_WATCH_STATUS,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId, serialId) != 0;
    }

    private static final String FIND_SERIALS_THAT_I_WATCH = SELECT_ALL_SERIAL_FIELD + " JOIN viewed v on s.id = v.serial_id WHERE v.user_id = ? LIMIT ? OFFSET ?";
    @Override
    public List<Serial> findSerialsThatUserWatch(String userId, int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIALS_THAT_I_WATCH,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), userId, limit, offset);
    }

    private static final String COUNT_ALL_SERIALS_THAT_I_WATCH = "SELECT COUNT(*) FROM viewed WHERE user_id = ?";
    @Override
    public int countAllSerialsThatUserWatch(String userId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_SERIALS_THAT_I_WATCH,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId);
    }

    private static final String FIND_SERIALS_THAT_I_LIKED = SELECT_ALL_SERIAL_FIELD + " JOIN liked l on s.id = l.serial_id WHERE l.user_id = ? LIMIT ? OFFSET ?";
    @Override
    public List<Serial> findSerialsThatUserLiked(String userId, int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_SERIALS_THAT_I_LIKED,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), userId, limit, offset);
    }

    private static final String COUNT_ALL_SERIALS_THAT_I_LIKED = "SELECT COUNT(*) FROM liked WHERE user_id = ?";
    @Override
    public int countAllSerialsThatUserLiked(String userId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_SERIALS_THAT_I_LIKED,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId);
    }

    private static final String LIKE_SERIAL = "UPDATE serial SET count_like = count_like + 1 WHERE id = ?";
    private static final String ADD_IN_LIKED = "INSERT INTO liked VALUES (?, ?)";
    @Override
    public boolean likeSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), LIKE_SERIAL, serialId) &&
                JDBCUtil.execute(transaction.getConnection(), ADD_IN_LIKED, userId, serialId);
    }

    private static final String DISLIKE_SERIAL = "UPDATE serial SET count_like = count_like - 1 WHERE id = ?";
    private static final String REMOVE_IN_LIKED = "DELETE FROM liked WHERE (user_id = ? and serial_id = ?)";
    @Override
    public boolean dislikeSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DISLIKE_SERIAL, serialId) &&
                JDBCUtil.execute(transaction.getConnection(), REMOVE_IN_LIKED, userId, serialId);
    }

    private static final String SERIAL_IS_LIKED_STATUS = SELECT_ALL_SERIAL_FIELD + " JOIN liked l on s.id = l.serial_id WHERE l.user_id = ? and l.serial_id = ?";
    @Override
    public boolean userLikedThisSerial(String userId, String serialId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), SERIAL_IS_LIKED_STATUS,
                ResultSetHandlerFactory.getCountResultSetHandler(), userId, serialId) != 0;
    }
}
