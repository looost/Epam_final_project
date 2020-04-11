package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {

    private ResultSetHandlerFactory() {
    }

    public static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws DaoException {
                try {
                    if (rs.next()) {
                        return oneRowResultSetHandler.handle(rs);
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        };
    }

    public static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> manyRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet rs) throws DaoException {
                try {
                    List<T> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(manyRowResultSetHandler.handle(rs));
                    }
                    return list;
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        };
    }

    public static ResultSetHandler<Integer> getCountResultSetHandler() {
        return new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws DaoException {
                try {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return 0;
                    }
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        };
    }

}
