package by.training.dao;

import by.training.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import static by.training.utils.ConstantName.DEBUG_LOGGER;

/**
 * A class that provides access to a data source. Implements {@link AutoCloseable} interface.
 *
 * @see Connection
 */
public class Transaction implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);
    /**
     * A connection object that provides access to database.
     */
    private final Connection connection;

    /**
     * Initialize connection from connection pool.
     */
    public Transaction() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    /**
     * The method through which access to connection.
     *
     * @return the connection
     * @throws DaoException if cannot set auto commit
     */
    public Connection getConnection() throws DaoException {
        try {
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new DaoException("Auto commit problem", e);
        }
    }

    /**
     * Makes all changes made since the previous commit or rollback permanent.
     *
     * @throws DaoException if cannot commit connection
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Cannot commit", e);
        }
    }

    /**
     * Undoes all changes made in the current transaction.
     *
     * @throws DaoException if cannot rollback connection
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Cannot rollback", e);
        }
    }

    /**
     * Returns connection to connection pool.
     * Implementation of {@link AutoCloseable} interface to work try with resources.
     *
     * @throws DaoException if cannot rollback or close connection
     */
    @Override
    public void close() throws DaoException {
        try {
            if (connection != null) {
                connection.rollback();
                //logger.debug("rollback connect");
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close connection", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    //logger.debug("close connect");
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }


}
