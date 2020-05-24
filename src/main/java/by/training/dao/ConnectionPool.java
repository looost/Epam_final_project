package by.training.dao;

import by.training.dao.exception.DaoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TomCat connection pool class.
 */
public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Method for getting connection.
     *
     * @return the connection from connection pool
     * @throws DaoException if you could not get the connection
     */
    public Connection getConnection() throws DaoException {
        Context ctx;
        Connection c;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/serial");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new DaoException(e);
        }
        return c;
    }
}
