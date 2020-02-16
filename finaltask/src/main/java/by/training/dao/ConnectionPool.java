package by.training.dao;

import by.training.dao.exception.DaoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/serials_db?serverTimezone=UTC", "application", "admin");
    }


//    public Connection getConnection() throws DaoException {
//        Context ctx;
//        Connection connection = null;
//        try {
//            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
//            DataSource ds = (DataSource) envCtx.lookup("jdbc/serials");
//            connection = ds.getConnection();
//            ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/serials");
//            connection = ds.getConnection();
//        } catch (NamingException e) {
//            throw new DaoException("NamingException from ConnectionPool", e);
//        } catch (SQLException e) {
//            throw new DaoException("SQLException from ConnectionPool", e);
//        }
//        return connection;
//    }
}
