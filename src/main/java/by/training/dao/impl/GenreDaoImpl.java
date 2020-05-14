package by.training.dao.impl;

import by.training.dao.GenreDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link GenreDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Genre}.
 *
 * @see Transaction
 * @see DaoException
 */
public class GenreDaoImpl implements GenreDao {

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Genre} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Genre> GENRE_RESULT_SET_HANDLER = new ResultSetHandler<Genre>() {
        @Override
        public Genre handle(final ResultSet rs) throws DaoException {
            Genre genre = new Genre();
            try {
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                return genre;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public GenreDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_GENRE_BY_NAME = "SELECT id, name FROM genre WHERE name = ?";

    /**
     * Find genre by name.
     *
     * @param name the genre name
     * @return the ${@link Genre} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Genre findByName(final String name) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_GENRE_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), name);
    }

    private static final String FIND_ALL_GENRE = "SELECT id, name FROM genre";

    /**
     * Find all genre.
     *
     * @return the list of genres and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Genre> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_GENRE,
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER));
    }

    private static final String FIND_GENRE_PAGE_BY_PAGE = "SELECT id, name FROM genre ORDER BY name LIMIT ? OFFSET ?";

    /**
     * Find genres page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of genres and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Genre> findGenrePageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_GENRE_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String COUNT_ALL_GENRE = "SELECT COUNT(*) FROM genre";

    /**
     * Count all genres.
     *
     * @return number of all genres
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllGenres() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_GENRE,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    private static final String FIND_GENRES_BY_SERIAL_ID = "SELECT g.id, g.name FROM genre g JOIN serial_genre sg on g.id = sg.genre_id where serial_id = ?";

    /**
     * Find genres by serial id.
     *
     * @param serialId the serial id
     * @return the list of genres and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     * @see by.training.model.Serial
     */
    @Override
    public List<Genre> findGenreBySerialId(final String serialId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_GENRES_BY_SERIAL_ID,
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER), serialId);
    }

    private static final String FIND_GENRE_BY_ID = "SELECT id, name FROM genre WHERE id = ?";

    /**
     * Find genre by id.
     *
     * @param id the genre id
     * @return the ${@link Genre} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Genre findById(final String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_GENRE_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_GENRE_BY_ID = "DELETE FROM genre WHERE id = ?";

    /**
     * Delete genre by id.
     *
     * @param id the genre id
     * @return true if genre was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_GENRE_BY_ID, id);
    }

    private static final String CREATE_GENRE_BY_ID = "INSERT INTO genre VALUES (DEFAULT, ?)";

    /**
     * Create genre.
     *
     * @param entity the genre
     * @return true if genre was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Genre entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_GENRE_BY_ID, entity.getName());
    }

    private static final String UPDATE_GENRE_BY_ID = "UPDATE genre SET name = ? WHERE id = ?";

    /**
     * Update genre.
     *
     * @param entity the genre
     * @return true if genre was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Genre entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_GENRE_BY_ID,
                entity.getName(), String.valueOf(entity.getId()));
    }
}
