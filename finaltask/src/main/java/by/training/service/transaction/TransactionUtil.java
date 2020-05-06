package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;


/**
 * Util class.
 */
public final class TransactionUtil {

    private TransactionUtil() {
    }

    /**
     * Method to get data from DAO layer.
     *
     * @param <T>         single or {@link java.util.List} {@link by.training.model.AbstractEntity}
     * @param transaction object to access data
     * @param builder     the Transaction builder
     * @param entity      the {@link by.training.model.AbstractEntity}
     * @return the {@link by.training.model.AbstractEntity}
     * @throws ServiceException if the method failed
     */
    public static <T> T select(final Transaction transaction, final TransactionBuilder<T> builder,
                               final T entity) throws ServiceException {
        return builder.transaction(transaction, entity);
    }

}
