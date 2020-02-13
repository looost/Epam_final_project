package by.training.dao.serial.impl;

import by.training.dao.ConnectionFactory;
import by.training.dao.exception.DaoException;
import by.training.dao.serial.SerialDao;
import by.training.model.Genre;
import by.training.model.Serial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SerialDaoImpl implements SerialDao {

    private static final Logger logger = LogManager.getLogger("exception");

    //language=SQL
    private static final String SQL_FIND_SHOW_BY_NAME = "SELECT s.*, sg.genre_id FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE name = ?";
    //language=SQL
    private static final String SQL_FIND_SHOW_BY_ID = "SELECT s.*, sg.genre_id FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id WHERE id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_SHOW = "SELECT s.*, sg.genre_id FROM serial s JOIN serial_genre sg ON s.id = sg.serial_id";
    //language=SQL
    private static final String SQL_DELETE_SHOW_BY_ID = "DELETE FROM serial WHERE id = ?";
    //language=SQL
    private static final String SQL_CREATE_SHOW = "INSERT INTO serial VALUES (DEFAULT, ?, ?, ?, ?)"; // TODO Как посутпать с жанрами? Передовать коллекцию в конструктор или по одному?
    //language=SQL
    private static final String SQL_UPDATE_SHOW = "UPDATE serial SET name = ?, description = ?, logo = ?, full_logo = ? WHERE id = ?";


    @Override
    public Serial findSerialByName(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_SHOW_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Serial serial = new Serial();
            while (resultSet.next()) {
                if (serial.getName() == null) {
                    genreSet.add(new Genre(resultSet.getInt("genre_id")));
                    serial = new Serial(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"),
                            resultSet.getString("logo"), resultSet.getString("full_logo"), genreSet);
                }
                genreSet.add(new Genre(resultSet.getInt("genre_id")));
            }
            return serial;
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
    public List<Serial> findAll() throws DaoException { //TODO Коллекция?
        return null;
    }

    @Override
    public Serial findById(String id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_SHOW_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Serial serial = new Serial();
            while (resultSet.next()) {
                if (serial.getName() == null) {
                    genreSet.add(new Genre(resultSet.getInt("genre_id")));
                    serial = new Serial(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"),
                            resultSet.getString("logo"), resultSet.getString("full_logo"), genreSet);
                }
                genreSet.add(new Genre(resultSet.getInt("genre_id")));
            }
            return serial;
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
    public boolean delete(Serial entity) throws DaoException {
        return delete(String.valueOf(entity.getId()));
    }

    @Override
    public boolean create(Serial entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_SHOW);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getLogo());
            statement.setString(4, entity.getFull_logo());
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
    public boolean update(Serial entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_SHOW);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getLogo());
            statement.setString(3, entity.getFull_logo());
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
