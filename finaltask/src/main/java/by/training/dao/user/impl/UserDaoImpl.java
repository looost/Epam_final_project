package by.training.dao.user.impl;

import by.training.dao.ConnectionFactory;
import by.training.dao.exception.DaoException;
import by.training.dao.user.UserDao;
import by.training.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger("exception");

    //language=SQL
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_USER = "SELECT * FROM user";
    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    //language=SQL
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    //language=SQL
    private static final String SQL_CREATE_USER = "INSERT INTO user VALUES (DEFAULT, ?, ?, 2)";
    //language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE user SET login = ?, password = ? WHERE id = ?";


    @Override
    public User findByLogin(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getInt("role"));
            }
            return new User();
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_USER);
            resultSet = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getInt("role")));
            }
            return userList;
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public User findById(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getInt("role"));
            }
            logger.error("User not found");
            throw new DaoException("User not found"); // TODO Ошибка или пустой юзер?
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean delete(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        return delete(String.valueOf(entity.getId()));
    }

    @Override
    public boolean create(User entity) throws DaoException {  // TODO Нужна ли проверка есть ли пользователь с таким логином?
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            return statement.execute();
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean update(User entity) throws DaoException { // TODO Нужна ли проверка есть ли пользователь с таким логином?
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, String.valueOf(entity.getId()));
            return statement.execute();
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
