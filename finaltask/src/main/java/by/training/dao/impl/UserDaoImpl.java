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

public class UserDaoImpl implements UserDao {

    private Transaction transaction;

    private static final ResultSetHandler<User> USER_RESULT_SET_HANDLER = new ResultSetHandler<User>() {
        @Override
        public User handle(ResultSet rs) throws DaoException {
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

    public UserDaoImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_USER_BY_LOGIN = "SELECT id, login, password, avatar, role FROM user WHERE login = ?";
    @Override
    public User findByLogin(String login) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_USER_BY_LOGIN,
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), login);
    }

    private static final String FIND_USER_BY_ID = "SELECT id, login, password, avatar, role FROM user WHERE id = ?";
    @Override
    public User findById(String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_USER_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_USER_BY_ID, id);
    }

    private static final String CREATE_USER = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?)";
    @Override
    public boolean create(User entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_USER, entity.getLogin(), entity.getPassword(),
                entity.getAvatar(), entity.getRole());
    }

    private static final String UPDATE_USER = "UPDATE user SET password = ?, avatar = ? WHERE login = ?";
    @Override
    public boolean update(User entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_USER, entity.getPassword(),
                entity.getAvatar(), entity.getLogin());
    }
}
