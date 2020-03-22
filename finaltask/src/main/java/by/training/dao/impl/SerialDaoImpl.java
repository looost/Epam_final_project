package by.training.dao.impl;

import by.training.dao.SerialDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerialDaoImpl implements SerialDao {

    private Connection connection;

    private static final ResultSetHandler<Serial> SERIAL_RESULT_SET_HANDLER = new ResultSetHandler<Serial>() {
        @Override
        public Serial handle(ResultSet rs) throws DaoException {
            Serial serial = new Serial();
            try {
                serial.setId(rs.getInt("id"));
                serial.setName(rs.getString("name"));
                serial.setDescription(rs.getString("description"));
                serial.setLogo(rs.getString("logo"));
                serial.setFullLogo(rs.getString("full_logo"));
                serial.setReleaseDate(rs.getDate("release_date"));
                serial.setCountLike(rs.getInt("count_like"));
                Country country = new Country();
                country.setId(rs.getInt("country_id"));
                serial.setCountry(country);
                Studio studio = new Studio();
                studio.setId(rs.getInt("studio_id"));
                serial.setStudio(studio);
                serial.setGenres(new ArrayList<>());
                serial.setComments(new ArrayList<>());
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return serial;
        }
    };

    public SerialDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private static final String FIND_SERIAL_BY_NAME = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s WHERE s.name = ?";
    @Override
    public Serial findSerialByName(String name) throws DaoException {
        return JDBCUtil.select(connection, FIND_SERIAL_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), name);
    }

    private static final String FIND_ALL_SERIAL = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s";
    @Override
    public List<Serial> findAll() throws DaoException {
        return JDBCUtil.select(connection, FIND_ALL_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER));
    }

    private static final String FIND_ALL_SERIAL_2 = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s LIMIT ? OFFSET ?";
    @Override
    public List<Serial> findAllSerial2(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(connection, FIND_ALL_SERIAL_2,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String FIND_SERIAL_BY_SEARCH_QUERY = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s where (name like ? or description like ?)";
    @Override
    public List<Serial> findSerialBySearchForm(String searchQuery) throws DaoException {
        searchQuery = "%" + searchQuery + "%";
        return JDBCUtil.select(connection, FIND_SERIAL_BY_SEARCH_QUERY,
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), searchQuery, searchQuery);
    }

    private static final String FIND_SERIAL_BY_ID = "SELECT s.id, s.name, s.description, s.logo, s.full_logo," +
            " s.release_date, s.count_like, s.country_id, s.studio_id FROM serial s WHERE s.id = ?";
    @Override
    public Serial findById(String id) throws DaoException {
        return JDBCUtil.select(connection, FIND_SERIAL_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_SERIAL_BY_ID = "DELETE FROM serial WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, DELETE_SERIAL_BY_ID, id);
    }

    private static final String CREATE_SERIAL = "INSERT INTO serial VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?)";
    @Override
    public boolean create(Serial entity) throws DaoException {
        return JDBCUtil.create(connection, CREATE_SERIAL,
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(), entity.getReleaseDate(),
                entity.getCountry().getId(), entity.getStudio().getId());
    }

    @Override
    public int createAndReturnIndex(Serial serial) throws DaoException {
        return JDBCUtil.createAndReturnIndex(connection, CREATE_SERIAL,
                serial.getName(), serial.getDescription(), serial.getLogo(), serial.getFullLogo(), serial.getReleaseDate(),
                serial.getCountry().getId(), serial.getStudio().getId());
    }

    private static final String SERIAL_GENRE_VALUES = "INSERT INTO serial_genre VALUES (?, ?)";
    @Override
    public boolean createSerialGenre(int serialId, List<Genre> genres) throws DaoException {
        for (Genre g : genres
        ) {
            JDBCUtil.create(connection, SERIAL_GENRE_VALUES, serialId, g.getId());
        }
        return true;
    }

    private static final String UPDATE_SERIAL = "UPDATE serial SET name = ?, description = ?, logo = ?, full_logo = ? WHERE id = ?";
    @Override
    public boolean update(Serial entity) throws DaoException {
        return JDBCUtil.update(connection, UPDATE_SERIAL,
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(), entity.getId());
    }

}
