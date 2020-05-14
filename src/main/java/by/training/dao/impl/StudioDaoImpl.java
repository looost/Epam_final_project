package by.training.dao.impl;

import by.training.dao.StudioDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Studio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link StudioDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Studio}.
 *
 * @see Transaction
 * @see DaoException
 */
public class StudioDaoImpl implements StudioDao {

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Studio} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Studio> STUDIO_RESULT_SET_HANDLER = new ResultSetHandler<Studio>() {
        @Override
        public Studio handle(final ResultSet rs) throws DaoException {
            try {
                Studio studio = new Studio();
                studio.setId(rs.getInt("id"));
                studio.setName(rs.getString("name"));
                return studio;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public StudioDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_STUDIO_BY_NAME = "SELECT id, name FROM studio WHERE name = ?";

    /**
     * Find studio by name.
     *
     * @param studioName the studio name
     * @return the ${@link Studio} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Studio findByName(final String studioName) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), studioName);
    }

    private static final String FIND_ALL_STUDIO = "SELECT id, name FROM studio";

    /**
     * Find all studios.
     *
     * @return the list of studios and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Studio> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_STUDIO,
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER));
    }

    private static final String FIND_STUDIO_PAGE_BY_PAGE = "SELECT id, name FROM studio ORDER BY name LIMIT ? OFFSET ?";

    /**
     * Find studios page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of studios and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Studio> findStudioPageByPage(final int page, final int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String COUNT_ALL_STUDIO = "SELECT COUNT(*) FROM studio";

    /**
     * Count all studios.
     *
     * @return number of all studios
     * @throws DaoException if the method failed
     */
    @Override
    public int countAllStudio() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_STUDIO,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    private static final String FIND_STUDIO_BY_ID = "SELECT id, name FROM studio WHERE id = ?";

    /**
     * Find studio by id.
     *
     * @param id the studio id
     * @return the ${@link Studio} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Studio findById(final String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_STUDIO_BY_ID = "DELETE FROM studio WHERE id = ?";

    /**
     * Delete studio by id.
     *
     * @param id the studio id
     * @return true if studio was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_STUDIO_BY_ID, id);
    }

    private static final String CREATE_STUDIO = "INSERT INTO studio VALUES (DEFAULT, ?)";

    /**
     * Create studio.
     *
     * @param entity the studio
     * @return true if studio was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Studio entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_STUDIO, entity.getName());
    }

    private static final String UPDATE_STUDIO = "UPDATE studio SET name = ? WHERE id = ?";

    /**
     * Update studio.
     *
     * @param entity the studio
     * @return true if studio was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Studio entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_STUDIO, entity.getName(), entity.getId());
    }
}
