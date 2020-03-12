package by.training.service.transaction;

import by.training.service.exception.ServiceException;

import java.sql.Connection;

public class TransactionUtil {

    private TransactionUtil() {
    }

    public static <T> T select(Connection c, TransactionHandler<T> handler, T entity) throws ServiceException {
        return handler.transaction(c, entity);
    }


}
