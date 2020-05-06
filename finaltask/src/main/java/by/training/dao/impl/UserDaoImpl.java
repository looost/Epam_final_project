package by.training.dao.impl;

import by.training.dao.Transaction;
import by.training.dao.UserDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of {@link UserDao} interface. Provides access to the database
 * and provides support for working with the entity {@link User}.
 *
 * @see Transaction
 * @see DaoException
 */
public class UserDaoImpl implements UserDao {

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link User} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<User> USER_RESULT_SET_HANDLER = new ResultSetHandler<User>() {
        @Override
        public User handle(final ResultSet rs) throws DaoException {
            User user = new User();
            try {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRole(rs.getInt("role"));
                return user;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public UserDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_USER_BY_LOGIN = "SELECT id, login, password, avatar, role FROM user WHERE login = ?";

    /**
     * Find user by login.
     *
     * @param login the user login
     * @return the ${@link User} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public User findByLogin(final String login) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_USER_BY_LOGIN,
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), login);
    }

    private static final String FIND_USER_BY_ID = "SELECT id, login, password, avatar, role FROM user WHERE id = ?";

    /**
     * Find user by id.
     *
     * @param id the user id
     * @return the ${@link User} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public User findById(final String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_USER_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";

    /**
     * Delete user by id.
     *
     * @param id the user id
     * @return true if user was made deleted and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_USER_BY_ID, id);
    }

    private static final String CREATE_USER = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?)";

    /**
     * Create user.
     *
     * @param entity the user
     * @return true if user was made created and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final User entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_USER, entity.getLogin(), entity.getPassword(),
                entity.getAvatar(), entity.getRole());
    }

    private static final String UPDATE_USER = "UPDATE user SET password = ?, avatar = ? WHERE login = ?";

    /**
     * Update user.
     *
     * @param entity the user
     * @return true if user was made updated and false otherwise.
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final User entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_USER, entity.getPassword(),
                entity.getAvatar(), entity.getLogin());
    }
}
