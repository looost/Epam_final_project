package by.training.dao;

import java.sql.Connection;

public abstract class Dao {

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
