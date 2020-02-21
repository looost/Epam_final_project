package by.training.dao.genre.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.genre.GenreDao;
import by.training.model.Genre;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenreDaoImpl implements GenreDao {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/sqlGenre.properties";

    private Connection connection;

    public GenreDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Genre findByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findGenreByName"));
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Genre(resultSet.getInt(getProperties().getProperty("id")),
                        resultSet.getString(getProperties().getProperty("genreName")));
            }
            return new Genre();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllGenre"));
            resultSet = statement.executeQuery();
            List<Genre> genreList = new ArrayList<>();
            while (resultSet.next()) {
                genreList.add(new Genre(resultSet.getInt(getProperties().getProperty("id")),
                        resultSet.getString(getProperties().getProperty("genreName"))));
            }
            return genreList;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public Genre findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findGenreById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Genre(resultSet.getInt(getProperties().getProperty("id")),
                        resultSet.getString(getProperties().getProperty("genreName")));
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
            statement = connection.prepareStatement(getProperties().getProperty("deleteGenreById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Genre entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createGenreById"));
            statement.setString(1, entity.getName());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(Genre entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateGenreById"));
            statement.setString(1, entity.getName());
            statement.setString(2, String.valueOf(entity.getId()));
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
