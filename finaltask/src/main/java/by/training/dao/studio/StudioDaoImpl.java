package by.training.dao.studio;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.Studio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudioDaoImpl implements AbstractDao<String, Studio> {

    private static final String PATH_TO_PROPERTIES = "D:\\Training\\finaltask\\src\\main\\resources\\sqlStudio.properties";

    private Connection connection;

    public StudioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Studio> findAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllStudio"));
            resultSet = statement.executeQuery();
            List<Studio> studioList = new ArrayList<>();
            while (resultSet.next()) {
                studioList.add(new Studio(resultSet.getInt(getProperties().getProperty("studioId")), resultSet.getString(getProperties().getProperty("studioName"))));
            }
            return studioList;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public Studio findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findStudioById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Studio studio = null;
            while (resultSet.next()) {
                studio = new Studio(resultSet.getInt(getProperties().getProperty("studioId")), resultSet.getString(getProperties().getProperty("studioName")));
            }
            return studio;
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
            statement = connection.prepareStatement(getProperties().getProperty("deleteStudioById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Studio entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createStudio"));
            statement.setString(1, entity.getName());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(Studio entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateStudio"));
            statement.setString(1, entity.getName());
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
