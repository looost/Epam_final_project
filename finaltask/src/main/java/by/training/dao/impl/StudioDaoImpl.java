package by.training.dao.impl;

import by.training.dao.StudioDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Studio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudioDaoImpl implements StudioDao {

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

    private static final String FIND_ALL_STUDIO = "SELECT id, name FROM studio";
    @Override
    public List<Studio> findAll() throws DaoException {
        return JDBCUtil.select(connection, FIND_ALL_STUDIO,
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER));
    }

    private static final String FIND_STUDIO_BY_ID = "SELECT id, name FROM studio WHERE id = ?";
    @Override
    public Studio findById(String id) throws DaoException {
        return JDBCUtil.select(connection, FIND_STUDIO_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_STUDIO_BY_ID = "DELETE FROM studio WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, DELETE_STUDIO_BY_ID, id);
    }

    private static final String CREATE_STUDIO = "INSERT INTO studio VALUES (DEFAULT, ?)";
    @Override
    public boolean create(Studio entity) throws DaoException {
        return JDBCUtil.create(connection, CREATE_STUDIO, entity.getName());
    }

    private static final String UPDATE_STUDIO = "UPDATE studio SET name = ? WHERE id = ?";
    @Override
    public boolean update(Studio entity) throws DaoException {
        return JDBCUtil.update(connection, UPDATE_STUDIO, entity.getName());
    }
}
