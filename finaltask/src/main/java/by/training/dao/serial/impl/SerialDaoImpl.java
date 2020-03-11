package by.training.dao.serial.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.jdbc.JDBCUtil;
import by.training.dao.jdbc.ResultSetHandler;
import by.training.dao.jdbc.ResultSetHandlerFactory;
import by.training.dao.serial.SerialDao;
import by.training.model.Serial;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class SerialDaoImpl implements SerialDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlSerial.properties";

    private Connection connection;
    private static final ResultSetHandler<Serial> SERIAL_RESULT_SET_HANDLER =
            ResultSetHandlerFactory.SERIAL_RESULT_SET_HANDLER;

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
