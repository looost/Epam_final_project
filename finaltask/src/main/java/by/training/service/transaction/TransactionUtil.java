package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;


public class TransactionUtil {

    private TransactionUtil() {
    }

    public static <T> T select(Transaction transaction, TransactionHandler<T> handler, T entity) throws ServiceException {
        return handler.transaction(transaction, entity);
    }

    public static <T> T create(Transaction transaction, TransactionHandler<T> handler, T entity) throws ServiceException {
        return handler.transaction(transaction, entity);
    }

}
