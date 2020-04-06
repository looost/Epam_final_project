package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.ResultSet;

@FunctionalInterface
public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws DaoException;
}
