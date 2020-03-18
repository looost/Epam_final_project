package by.training.dao.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.dao.SerialDao;
import by.training.model.Country;
import by.training.model.Serial;
import by.training.model.Studio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SerialDaoImpl implements SerialDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlSerial.properties";

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

    @Override
    public Serial findSerialByName(String name) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findSerialByName"),
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), name);
    }

    @Override
    public List<Serial> findAll() throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findAllSerial"),
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER));
    }

    @Override
    public List<Serial> findAllSerial2(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(connection, getProperties().getProperty("findAllSerial2"),
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), limit, offset);
    }

    @Override
    public List<Serial> findSerialBySearchForm(String searchQuery) throws DaoException {
        searchQuery = "%" + searchQuery + "%";
        return JDBCUtil.select(connection, getProperties().getProperty("findSerialBySearchQuery"),
                ResultSetHandlerFactory.getListResultSetHandler(SERIAL_RESULT_SET_HANDLER), searchQuery, searchQuery);
    }

    @Override
    public Serial findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findSerialById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(SERIAL_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteSerialById"), id);
    }

    @Override
    public boolean create(Serial entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createSerial"),
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(), entity.getReleaseDate(),
                entity.getCountry().getId(), entity.getStudio().getId());
    }

    @Override
    public boolean update(Serial entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateSerial"),
                entity.getName(), entity.getDescription(), entity.getLogo(), entity.getFullLogo(), entity.getId());
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
