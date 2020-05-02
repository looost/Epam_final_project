package by.training.dao.impl;

import by.training.dao.StudioDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Studio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudioDaoImpl implements StudioDao {

    private Transaction transaction;

    private static final ResultSetHandler<Studio> STUDIO_RESULT_SET_HANDLER = new ResultSetHandler<Studio>() {
        @Override
        public Studio handle(ResultSet rs) throws DaoException {
            Studio studio;
            try {
                studio = Studio.newBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .build();
//                studio.setId(rs.getInt("id"));
//                studio.setName(rs.getString("name"));
                return studio;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public StudioDaoImpl(Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_STUDIO_BY_NAME = "SELECT id, name FROM studio WHERE name = ?";

    @Override
    public Studio findByName(String studioName) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_BY_NAME,
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), studioName);
    }

    private static final String FIND_ALL_STUDIO = "SELECT id, name FROM studio";
    @Override
    public List<Studio> findAll() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_STUDIO,
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER));
    }

    private static final String FIND_STUDIO_PAGE_BY_PAGE = "SELECT id, name FROM studio ORDER BY name LIMIT ? OFFSET ?";

    @Override
    public List<Studio> findStudioPageByPage(int page, int limit) throws DaoException {
        int offset = (page - 1) * limit;
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_PAGE_BY_PAGE,
                ResultSetHandlerFactory.getListResultSetHandler(STUDIO_RESULT_SET_HANDLER), limit, offset);
    }

    private static final String COUNT_ALL_STUDIO = "SELECT COUNT(*) FROM studio";

    @Override
    public int countAllStudio() throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), COUNT_ALL_STUDIO,
                ResultSetHandlerFactory.getCountResultSetHandler());
    }

    private static final String FIND_STUDIO_BY_ID = "SELECT id, name FROM studio WHERE id = ?";
    @Override
    public Studio findById(String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_STUDIO_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(STUDIO_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_STUDIO_BY_ID = "DELETE FROM studio WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_STUDIO_BY_ID, id);
    }

    private static final String CREATE_STUDIO = "INSERT INTO studio VALUES (DEFAULT, ?)";
    @Override
    public boolean create(Studio entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_STUDIO, entity.getName());
    }

    private static final String UPDATE_STUDIO = "UPDATE studio SET name = ? WHERE id = ?";
    @Override
    public boolean update(Studio entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_STUDIO, entity.getName(), entity.getId());
    }
}
