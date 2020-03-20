package by.training.dao.impl;

import by.training.dao.CommentDao;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private Connection connection;

    private static final ResultSetHandler<Comment> COMMENT_RESULT_SET_HANDLER = new ResultSetHandler<Comment>() {
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

    public CommentDaoImpl(Connection connection) {
        this.connection = connection;
    }


    private static final String FIND_ALL_COMMENT_FOR_SERIAL = "SELECT id, user_id, serial_id, comment, publication_date FROM comment" +
            " WHERE serial_id = ? order by publication_date desc";
    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws DaoException {
        return JDBCUtil.select(connection, FIND_ALL_COMMENT_FOR_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(COMMENT_RESULT_SET_HANDLER), serialId);
    }

    private static final String FIND_COMMENT_BY_ID = "SELECT id, user_id, serial_id, comment, publication_date FROM comment WHERE id = ?";
    @Override
    public Comment findById(String id) throws DaoException {
        return JDBCUtil.select(connection, FIND_COMMENT_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(COMMENT_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_COMMENT_BY_ID = "DELETE FROM comment WHERE id = ?";
    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, DELETE_COMMENT_BY_ID, id);
    }

    private static final String CREATE_COMMENT = "INSERT INTO comment VALUES (DEFAULT, ?, ?, ?, DEFAULT)";
    @Override
    public boolean create(Comment entity) throws DaoException {
        return JDBCUtil.create(connection, CREATE_COMMENT,
                String.valueOf(entity.getUser().getId()), String.valueOf(entity.getSerial().getId()), entity.getComment());
    }

    private static final String UPDATE_COMMENT = "UPDATE comment SET comment = ? WHERE id = ?";
    @Override
    public boolean update(Comment entity) throws DaoException {
        return JDBCUtil.update(connection, UPDATE_COMMENT, entity.getComment());
    }
}
