package by.training.dao.impl;

import by.training.dao.StudioDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Studio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class StudioDaoImpl implements StudioDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlStudio.properties";

    private Connection connection;

    private static final ResultSetHandler<Studio> STUDIO_RESULT_SET_HANDLER = new ResultSetHandler<Studio>() {
        @Override
        public Studio handle(ResultSet rs) throws DaoException {
            Studio studio = new Studio();
            try {
                studio.setId(rs.getInt("id"));
                studio.setName(rs.getString("name"));
                return studio;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public StudioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Studio> findAll() throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findAllStudio"),
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER));
    }

    @Override
    public Studio findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findStudioById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteStudioById"), id);
    }

    @Override
    public boolean create(Studio entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createStudio"), entity.getName());
    }

    @Override
    public boolean update(Studio entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateStudio"), entity.getName());
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
