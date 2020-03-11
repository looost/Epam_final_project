package by.training.dao.user.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.user.UserDao;
import by.training.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl implements UserDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlUser.properties";
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findUserByLogin"));
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(getProperties().getProperty("userId")), resultSet.getString(getProperties().getProperty("userLogin")),
                        resultSet.getString(getProperties().getProperty("userPassword")), resultSet.getInt(getProperties().getProperty("userRole")));
            }
            return new User();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findUserByLoginAndPassword"));
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(getProperties().getProperty("userId")), resultSet.getString(getProperties().getProperty("userLogin")),
                        resultSet.getString(getProperties().getProperty("userPassword")), resultSet.getInt(getProperties().getProperty("userRole")));
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllUser"));
            resultSet = statement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt(getProperties().getProperty("userId")), resultSet.getString(getProperties().getProperty("userLogin")),
                        resultSet.getString(getProperties().getProperty("userPassword")), resultSet.getInt(getProperties().getProperty("userRole"))));
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public User findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findUserById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(getProperties().getProperty("userId")), resultSet.getString(getProperties().getProperty("userLogin")),
                        resultSet.getString(getProperties().getProperty("userPassword")), resultSet.getInt(getProperties().getProperty("userRole")));
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public boolean delete(String id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("deleteUserById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createUser"));
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateUser"));
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, String.valueOf(entity.getId()));
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean updateUserRole(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateRoleUser"));
            statement.setString(1, String.valueOf(user.getRole()));
            statement.setString(2, String.valueOf(user.getId()));
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    private void close(ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close resultSet", e);
        }
    }

    private void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close statement", e);
        }
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
