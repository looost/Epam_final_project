package by.training.service;

import by.training.model.Entity;
import by.training.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AbstractService<KEY, ENTITY extends Entity> {
    Logger logger = LogManager.getLogger("exception");

    List<ENTITY> findAll() throws ServiceException;

    ENTITY findById(KEY id) throws ServiceException;

    boolean delete(KEY id) throws ServiceException;

    boolean delete(ENTITY entity) throws ServiceException;

    boolean create(ENTITY entity) throws ServiceException;

    boolean update(ENTITY entity) throws ServiceException;


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
