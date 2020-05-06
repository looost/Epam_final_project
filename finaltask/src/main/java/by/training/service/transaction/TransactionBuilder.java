package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;

/**
 * Functional interface which help build {@link by.training.model.AbstractEntity} from different DAO classes.
 *
 * @param <T> the {@link by.training.model.AbstractEntity}
 */
@FunctionalInterface
public interface TransactionBuilder<T> {
    /**
     * A method that collects an {@link by.training.model.AbstractEntity} from different DAO classes.
     *
     * @param transaction object to access the data source
     * @param entity      the {@link by.training.model.AbstractEntity}
     * @return the {@link by.training.model.AbstractEntity}
     * @throws ServiceException if the method failed
     */
    T transaction(Transaction transaction, T entity) throws ServiceException;
}
