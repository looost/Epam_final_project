package by.training.dao.impl.jdbc;

import by.training.dao.exception.DaoException;

import java.sql.*;

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
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            populatePrepareStatement(ps, param);
            int f = ps.executeUpdate();
            System.out.println(f + "!!!!");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            } else {
                System.out.println("FALSE");
            }
            return true;
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
}
