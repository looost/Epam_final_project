package by.training.dao;

import by.training.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {

    private final Connection connection;

    public Transaction() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public Connection getConnection() throws DaoException {
        try {
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new DaoException("Auto commit problem", e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Cannot commit", e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close connection", e);
        }
    }


}
