package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.*;
import java.util.List;

public class JDBCUtil {

    private JDBCUtil() {
    }

    public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            ResultSet rs = ps.executeQuery();
            return resultSetHandler.handle(rs);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public static boolean create(Connection c, String sql, Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static int createAndReturnIndex(Connection c, String sql, Object... param) throws DaoException {
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

    public static boolean update(Connection c, String sql, Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            return ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static boolean delete(Connection c, String sql, Object... param) throws DaoException {
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            populatePrepareStatement(ps, param);
            return ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static void populatePrepareStatement(PreparedStatement ps, Object... params) throws DaoException {
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

    public static void populateSqlAndParams(StringBuilder sql, List<String> params, List<Integer> list, String expression) {
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
