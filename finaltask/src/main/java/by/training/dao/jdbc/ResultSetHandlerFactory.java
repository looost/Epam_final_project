package by.training.dao.jdbc;

import by.training.dao.exception.DaoException;
import by.training.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {

    private ResultSetHandlerFactory() {
    }

    public static final ResultSetHandler<Serial> SERIAL_RESULT_SET_HANDLER = new ResultSetHandler<Serial>() {
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

    public static final ResultSetHandler<Comment> COMMENT_RESULT_SET_HANDLER = new ResultSetHandler<Comment>() {
        @Override
        public Comment handle(ResultSet rs) throws DaoException {
            Comment comment = new Comment();
            try {
                comment.setId(rs.getInt("id"));
                User user = new User();
                user.setId(rs.getInt("user_id"));
                comment.setUser(user);
                Serial serial = new Serial();
                serial.setId(rs.getInt("serial_id"));
                comment.setSerial(serial);
                comment.setComment(rs.getString("comment"));
                comment.setPublicationDate(rs.getDate("publication_date"));
                return comment;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public static final ResultSetHandler<Genre> GENRE_RESULT_SET_HANDLER = new ResultSetHandler<Genre>() {
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

    public static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws DaoException {
                try {
                    if (rs.next()) {
                        return oneRowResultSetHandler.handle(rs);
                    } else {
                        return null;
                    }
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        };
    }

    public static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> manyRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet rs) throws DaoException {
                try {
                    List<T> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(manyRowResultSetHandler.handle(rs));
                    }
                    return list;
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        };
    }

}
