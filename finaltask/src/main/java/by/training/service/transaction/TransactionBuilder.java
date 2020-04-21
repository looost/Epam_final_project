package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;

@FunctionalInterface
public interface TransactionBuilder<T> {
    T transaction(Transaction transaction, T entity) throws ServiceException;
}