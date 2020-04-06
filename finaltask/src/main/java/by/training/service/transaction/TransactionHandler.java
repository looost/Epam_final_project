package by.training.service.transaction;

import by.training.service.exception.ServiceException;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionHandler<T> {
    T transaction(Connection c, T entity) throws ServiceException;
}
