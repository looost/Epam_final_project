package by.training.dao.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;

import java.sql.Connection;


public class ViewedDao {
    private Connection connection;

    public ViewedDao(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String userId, String serialId) throws DaoException {
        String sql = "INSERT INTO viewed VALUES (?, ?)";
        JDBCUtil.create(connection, sql, userId, serialId);
        return true;
    }

    public boolean delete(String userId, String serialId) throws DaoException {
        String sql = "DELETE FROM viewed WHERE (user_id = ? and serial_id = ?)";
        JDBCUtil.delete(connection, sql, userId, serialId);
        return true;
    }

}
