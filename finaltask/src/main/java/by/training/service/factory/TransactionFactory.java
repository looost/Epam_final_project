package by.training.service.factory;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.Transaction;
import by.training.service.transaction.impl.TransactionImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {

    private Connection connection;

    public TransactionFactory() {
        try {
            this.connection = ConnectionPool.getInstance().getConnection();
            this.connection.setAutoCommit(false);
        } catch (SQLException | DaoException e) {

        }
    }

    public Transaction createTransaction() throws ServiceException {
        return new TransactionImpl(this.connection);
    }
}
