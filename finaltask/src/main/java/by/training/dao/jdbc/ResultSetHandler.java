package by.training.dao.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws DaoException;
}