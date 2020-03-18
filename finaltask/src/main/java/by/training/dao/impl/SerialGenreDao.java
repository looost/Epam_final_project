package by.training.dao.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.model.Genre;
import by.training.model.Serial;

import java.sql.Connection;

public class SerialGenreDao {

    private Connection connection;

    public SerialGenreDao(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Serial serial) throws DaoException {
        String sql = "INSERT INTO serial_genre VALUES (?, ?)";
        for (Genre g : serial.getGenres()
        ) {
            JDBCUtil.create(connection, sql, serial.getId(), g.getId());
        }
        return true;
    }

}
