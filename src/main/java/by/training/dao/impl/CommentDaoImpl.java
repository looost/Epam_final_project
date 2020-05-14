package by.training.dao.impl;

import by.training.dao.CommentDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.impl.jdbc.JDBCUtil;
import by.training.dao.impl.jdbc.ResultSetHandler;
import by.training.dao.impl.jdbc.ResultSetHandlerFactory;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of {@link CommentDao} interface. Provides access to the database
 * and provides support for working with the entity {@link Comment}.
 *
 * @see Transaction
 * @see DaoException
 */
public class CommentDaoImpl implements CommentDao {

    /**
     * An object that provides access to a data source.
     */
    private Transaction transaction;

    /**
     * Implementation of {@link ResultSetHandler} functional interface. Needs for build {@link Comment} from result set.
     *
     * @see ResultSet
     */
    private static final ResultSetHandler<Comment> COMMENT_RESULT_SET_HANDLER = new ResultSetHandler<Comment>() {
        @Override
        public Comment handle(final ResultSet rs) throws DaoException {
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
                comment.setPublicationDate(rs.getTimestamp("publication_date").toLocalDateTime());
                return comment;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    };

    public CommentDaoImpl(final Transaction transaction) {
        this.transaction = transaction;
    }

    private static final String FIND_ALL_COMMENT_FOR_SERIAL = "SELECT id, user_id, serial_id, comment, publication_date FROM comment" +
            " WHERE serial_id = ? order by publication_date desc";

    /**
     * Find all comment for serial.
     *
     * @param serialId the serial id
     * @return the list of ${@link Comment} and empty {@link List} if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public List<Comment> findAllCommentForSerial(final String serialId) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_ALL_COMMENT_FOR_SERIAL,
                ResultSetHandlerFactory.getListResultSetHandler(COMMENT_RESULT_SET_HANDLER), serialId);
    }

    private static final String FIND_COMMENT_BY_ID = "SELECT id, user_id, serial_id, comment, publication_date FROM comment WHERE id = ?";

    /**
     * Find comment by id.
     *
     * @param id the comment id
     * @return the ${@link Comment} and null if no results are found
     * @throws DaoException if the method failed
     */
    @Override
    public Comment findById(final String id) throws DaoException {
        return JDBCUtil.select(transaction.getConnection(), FIND_COMMENT_BY_ID,
                ResultSetHandlerFactory.getSingleResultSetHandler(COMMENT_RESULT_SET_HANDLER), id);
    }

    private static final String DELETE_COMMENT_BY_ID = "DELETE FROM comment WHERE id = ?";

    /**
     * Delete comment by id.
     *
     * @param id the comment id
     * @return true if comment was made deleted and false otherwise
     * @throws DaoException if the method failed
     */
    @Override
    public boolean delete(final String id) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), DELETE_COMMENT_BY_ID, id);
    }

    private static final String CREATE_COMMENT = "INSERT INTO comment VALUES (DEFAULT, ?, ?, ?, DEFAULT)";

    /**
     * Create comment.
     *
     * @param entity the comment
     * @return true if comment was made created and false otherwise
     * @throws DaoException if the method failed
     */
    @Override
    public boolean create(final Comment entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), CREATE_COMMENT,
                String.valueOf(entity.getUser().getId()), String.valueOf(entity.getSerial().getId()), entity.getComment());
    }

    private static final String UPDATE_COMMENT = "UPDATE comment SET comment = ? WHERE id = ?";

    /**
     * Update comment.
     *
     * @param entity the comment
     * @return true if comment was made updated and false otherwise
     * @throws DaoException if the method failed
     */
    @Override
    public boolean update(final Comment entity) throws DaoException {
        return JDBCUtil.execute(transaction.getConnection(), UPDATE_COMMENT, entity.getComment(), entity.getId());
    }
}
