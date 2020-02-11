package by.training.dao.genre.impl;

import by.training.dao.ConnectionFactory;
import by.training.dao.exception.DaoException;
import by.training.dao.genre.GenreDao;
import by.training.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

    private static final Logger logger = LogManager.getLogger("exception");

    //language=SQL
    private static final String SQL_FIND_GENRE_BY_NAME = "SELECT * FROM genre WHERE name = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_GENRE = "SELECT * FROM genre";
    //language=SQL
    private static final String SQL_FIND_GENRE_BY_ID = "SELECT * FROM genre WHERE id = ?";
    //language=SQL
    private static final String SQL_DELETE_GENRE_BY_ID = "DELETE FROM genre WHERE id = ?";
    //language=SQL
    private static final String SQL_CREATE_GENRE = "INSERT INTO genre VALUES (DEFAULT, ?)";
    //language=SQL
    private static final String SQL_UPDATE_GENRE = "UPDATE genre SET name = ? WHERE id = ?";

    @Override
    public Genre findByName(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_GENRE_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Genre(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return new Genre();
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
    public List<Genre> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_GENRE);
            resultSet = statement.executeQuery();
            List<Genre> genreList = new ArrayList<>();
            while (resultSet.next()) {
                genreList.add(new Genre(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return genreList;
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
    public Genre findById(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_GENRE_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Genre(resultSet.getInt("id"), resultSet.getString("name"));
            }
            logger.error("User not found");
            throw new DaoException("User not found");
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
            statement = connection.prepareStatement(SQL_DELETE_GENRE_BY_ID);
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
    public boolean delete(Genre entity) throws DaoException {
        return delete(String.valueOf(entity.getId()));
    }

    @Override
    public boolean create(Genre entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_GENRE);
            statement.setString(1, entity.getName());
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
    public boolean update(Genre entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_GENRE);
            statement.setString(1, entity.getName());
            statement.setString(2, String.valueOf(entity.getId()));
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
