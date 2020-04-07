package by.training.dao;

import by.training.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    public Connection getConnection() throws DaoException {
        try {
            Connection c = ConnectionPool.getInstance().getConnection();
            c.setAutoCommit(false);
            return c;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit(Connection connection) throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void close(Connection connection) throws DaoException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
