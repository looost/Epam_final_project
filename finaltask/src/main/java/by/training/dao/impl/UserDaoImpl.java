package by.training.dao.impl;

import by.training.dao.UserDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserDaoImpl implements UserDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlUser.properties";
    private Connection connection;

    private static final ResultSetHandler<User> USER_RESULT_SET_HANDLER = new ResultSetHandler<User>() {
        @Override
        public User handle(ResultSet rs) throws DaoException {
            User user = new User();
            try {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                return user;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findUserByLogin"),
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findUserByLoginAndPassword"),
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), login, password);
    }

    @Override
    public User findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findUserById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(USER_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteUserById"), id);
    }

    @Override
    public boolean create(User entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createUser"), entity.getLogin(), entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateUser"), entity.getLogin(), entity.getPassword(), entity.getId());
    }

    @Override
    public boolean createUserWithRole(User user) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createUserWithRole"), user.getLogin(), user.getPassword(), user.getRole());
    }

    private Properties getProperties() throws DaoException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
            return properties;
        } catch (IOException e) {
            throw new DaoException("Not found properties file", e);
        }
    }
}
