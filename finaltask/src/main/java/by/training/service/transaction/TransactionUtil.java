package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;


public class TransactionUtil {

    private TransactionUtil() {
    }

    public static <T> T select(Transaction transaction, TransactionBuilder<T> builder, T entity) throws ServiceException {
        return builder.transaction(transaction, entity);
    }

    public static <T> T create(Transaction transaction, TransactionBuilder<T> builder, T entity) throws ServiceException {
        return builder.transaction(transaction, entity);
    }

}
