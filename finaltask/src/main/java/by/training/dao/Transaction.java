package by.training.dao;

import by.training.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    private Connection connection;

    public void startTransaction(Dao... dao) throws DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        for (Dao d : dao
        ) {
            d.setConnection(connection);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void close() throws DaoException {
        ConnectionPool.getInstance().close(connection);
    }

    public void test() {
        System.out.println(connection);
    }

}
