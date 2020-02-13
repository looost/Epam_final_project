package by.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory { // TODO Как правильно сделать фабрику connection
    private static final ConnectionFactory instance = new ConnectionFactory();

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/serials_db?serverTimezone=UTC", "application", "admin");
    }
}
