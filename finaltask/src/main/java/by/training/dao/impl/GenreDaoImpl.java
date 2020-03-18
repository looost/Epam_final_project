package by.training.dao.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.GenreDao;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Genre;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class GenreDaoImpl implements GenreDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlGenre.properties";

    private Connection connection;

    private static final ResultSetHandler<Genre> GENRE_RESULT_SET_HANDLER = new ResultSetHandler<Genre>() {
        @Override
        public Genre handle(ResultSet rs) throws DaoException {
            Genre genre = new Genre();
            try {
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                return genre;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public GenreDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Genre findByName(String name) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findGenreByName"),
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), name);
    }


    @Override
    public List<Genre> findAll() throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findAllGenre"),
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER));
    }

    @Override
    public List<Genre> findGenreBySerialId(String serialId) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findGenresBySerialId"),
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER), serialId);
    }

    @Override
    public Genre findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findGenreById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteGenreById"), id);
    }

    @Override
    public boolean create(Genre entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createGenreById"), entity.getName());
    }

    @Override
    public boolean update(Genre entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateGenreById"),
                entity.getName(), String.valueOf(entity.getId()));
    }

    private Properties getProperties() throws DaoException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
            return properties;
        } catch (IOException e) {
            throw new DaoException("Not found properties file", e);
        }
    }
}
