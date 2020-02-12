package by.training.dao.show.impl;

import by.training.dao.ConnectionFactory;
import by.training.dao.exception.DaoException;
import by.training.dao.show.ShowDao;
import by.training.entity.Genre;
import by.training.entity.Show;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShowDaoImpl implements ShowDao {

    private static final Logger logger = LogManager.getLogger("exception");


    //language=SQL
    private static final String SQL_FIND_SHOW_BY_NAME = "SELECT s.*, sg.genre_id FROM shows s JOIN show_genre sg ON s.id = sg.show_id WHERE name = ?";
    //language=SQL
    private static final String SQL_FIND_SHOW_BY_ID = "SELECT s.*, sg.genre_id FROM shows s JOIN show_genre sg ON s.id = sg.show_id WHERE id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_SHOW = "SELECT s.*, sg.genre_id FROM shows s JOIN show_genre sg ON s.id = sg.show_id";
    //language=SQL
    private static final String SQL_DELETE_SHOW_BY_ID = "DELETE FROM shows WHERE id = ?";
    //language=SQL
    private static final String SQL_CREATE_SHOW = "INSERT INTO shows VALUES (DEFAULT, ?, ?)"; // TODO Как посутпать с жанрами?
    //language=SQL
    private static final String SQL_UPDATE_SHOW = "UPDATE shows SET name = ?, rating = ? WHERE id = ?";


    @Override
    public Show findShowByName(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_SHOW_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Show show = new Show();
            while (resultSet.next()) {
                if (show.getName() == null) {
                    genreSet.add(new Genre(resultSet.getInt("genre_id")));
                    show = new Show(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("rating"), genreSet);
                }
                genreSet.add(new Genre(resultSet.getInt("genre_id")));
            }
            return show;
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
    public List<Show> findAll() throws DaoException { //TODO Коллекция?
        return null;
    }

    @Override
    public Show findById(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_SHOW_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Show show = new Show();
            while (resultSet.next()) {
                if (show.getName() == null) {
                    genreSet.add(new Genre(resultSet.getInt("genre_id")));
                    show = new Show(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("rating"), genreSet);
                }
                genreSet.add(new Genre(resultSet.getInt("genre_id")));
            }
            return show;
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
            statement = connection.prepareStatement(SQL_DELETE_SHOW_BY_ID);
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
    public boolean delete(Show entity) throws DaoException {
        return delete(String.valueOf(entity.getId()));
    }

    @Override
    public boolean create(Show entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_SHOW);
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getRating());
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
    public boolean update(Show entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_SHOW);
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getRating());
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
