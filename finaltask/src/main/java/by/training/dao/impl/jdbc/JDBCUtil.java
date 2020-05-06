package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Util class which help work with JDBC.
 */
public final class JDBCUtil {

    private static final Logger logger = LogManager.getLogger("debug");

    private JDBCUtil() {
    }

    /**
     * Method for select sql queries.
     *
     * @param c                the connection
     * @param sql              sql query
     * @param resultSetHandler the ResultSetHandler
     * @param param            array param to prepare statement
     * @param <T>              the {@link by.training.model.AbstractEntity}
     * @return the {@link by.training.model.AbstractEntity} or {@link List} {@link by.training.model.AbstractEntity}
     * @throws DaoException if have SQL exception
     */
    public static <T> T select(final Connection c, final String sql,
                               final ResultSetHandler<T> resultSetHandler, final Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            ResultSet rs = ps.executeQuery();
            return resultSetHandler.handle(rs);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method for delete, create or update sql queries.
     *
     * @param c     the connection
     * @param sql   sql query
     * @param param array param to prepare statement
     * @return true if data in table updated and false otherwise.
     * @throws DaoException if have SQL exception
     */
    public static boolean execute(final Connection c, final String sql, final Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            int res = ps.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method for batch delete, create or update sql queries.
     *
     * @param c         the connection
     * @param sql       sql query
     * @param paramList list of arrays param to prepare statement
     * @return true if data in table updated and false otherwise.
     * @throws DaoException if have SQL exception
     */
    public static boolean executeBatch(final Connection c, final String sql, final List<Object[]> paramList) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            for (Object[] param : paramList
            ) {
                populatePrepareStatement(ps, param);
                ps.addBatch();
            }
            int[] res = ps.executeBatch();
            return res.length != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method for delete, create or update sql queries.
     *
     * @param c     the connection
     * @param sql   sql query
     * @param param array param to prepare statement
     * @return id records which updated
     * @throws DaoException if have SQL exception
     */
    public static int executeAndReturnIndex(final Connection c, final String sql, final Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            populatePrepareStatement(ps, param);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DaoException("There is no autoincrement index after trying to add record into table");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Populate prepare statement.
     *
     * @param ps     prepare statement
     * @param params array param to prepare statement
     * @throws DaoException if have SQL exception
     */
    private static void populatePrepareStatement(final PreparedStatement ps, final Object... params) throws DaoException {
        try {
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Need for build dynamic search query.
     *
     * @param sql        template sql query
     * @param params     list of object param
     * @param list       list of id {@link by.training.model.AbstractEntity}
     * @param expression name of the field to fill
     */
    public static void populateSqlAndParams(final StringBuilder sql, final List<Object> params,
                                            final List<Integer> list, final String expression) {
        if (list != null && !list.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < list.size(); i++) {
                sql.append(expression);
                params.add(String.valueOf(list.get(i)));
                if (i != list.size() - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
    }
}
