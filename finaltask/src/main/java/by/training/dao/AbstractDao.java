package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AbstractDao<KEY, ENTITY extends Entity> {

    Logger logger = LogManager.getLogger("exception");

    List<ENTITY> findAll() throws DaoException;

    ENTITY findById(KEY id) throws DaoException;

    boolean delete(KEY id) throws DaoException;

    boolean delete(ENTITY entity) throws DaoException;

    boolean create(ENTITY entity) throws DaoException;

    boolean update(ENTITY entity) throws DaoException;


    default void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close resultSet", e);
        }
    }

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close statement", e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Cannot close connection", e);
        }
    }
}
