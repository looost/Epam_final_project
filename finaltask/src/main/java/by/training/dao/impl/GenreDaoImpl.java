package by.training.dao.impl;

import by.training.dao.GenreDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

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

    private static final String FIND_GENRE_BY_NAME = "SELECT id, name FROM genre WHERE name = ?";
    @Override
    public Genre findByName(String name) throws DaoException {
        return JDBCUtil.select(connection, FIND_GENRE_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), name);
    }

    private static final String FIND_ALL_GENRE = "SELECT id, name FROM genre";
    @Override
    public List<Genre> findAll() throws DaoException {
        return JDBCUtil.select(connection, FIND_ALL_GENRE,
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER));
    }

    private static final String FIND_GENRES_BY_SERIAL_ID = "SELECT g.id, g.name FROM genre g JOIN serial_genre sg on g.id = sg.genre_id where serial_id = ?";
    @Override
    public List<Genre> findGenreBySerialId(String serialId) throws DaoException {
        return JDBCUtil.select(connection, FIND_GENRES_BY_SERIAL_ID,
                ResultSetHandlerFactory.getListResultSetHandler(GENRE_RESULT_SET_HANDLER), serialId);
    }

    private static final String FIND_GENRE_BY_ID = "SELECT id, name FROM genre WHERE id = ?";
    @Override
    public Genre findById(String id) throws DaoException {
        return JDBCUtil.select(connection, FIND_GENRE_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(GENRE_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_GENRE_BY_ID = "DELETE FROM genre WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, DELETE_GENRE_BY_ID, id);
    }

    private static final String CREATE_GENRE_BY_ID = "INSERT INTO genre VALUES (DEFAULT, ?)";
    @Override
    public boolean create(Genre entity) throws DaoException {
        return JDBCUtil.create(connection, CREATE_GENRE_BY_ID, entity.getName());
    }

    private static final String UPDATE_GENRE_BY_ID = "UPDATE genre SET name = ? WHERE id = ?";
    @Override
    public boolean update(Genre entity) throws DaoException {
        return JDBCUtil.update(connection, UPDATE_GENRE_BY_ID,
                entity.getName(), String.valueOf(entity.getId()));
    }
}
