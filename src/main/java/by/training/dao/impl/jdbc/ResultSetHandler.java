package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.ResultSet;

/**
 * Functional interface which help build {@link by.training.model.AbstractEntity} from result set.
 *
 * @param <T> the {@link by.training.model.AbstractEntity}
 */
@FunctionalInterface
public interface ResultSetHandler<T> {
    /**
     * Result set based method that return {@link by.training.model.AbstractEntity}.
     *
     * @param rs result set
     * @return {@link by.training.model.AbstractEntity}
     * @throws DaoException if an error occurs during parsing result set
     */
    T handle(ResultSet rs) throws DaoException;
}
