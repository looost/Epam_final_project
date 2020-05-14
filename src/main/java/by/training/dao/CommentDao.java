package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Comment;

import java.util.List;

/**
 * Dao interface for {@link Comment}, all method throws {@link DaoException}.
 */
public interface CommentDao extends AbstractDao<String, Comment> {

    /**
     * Find all comment for serial.
     *
     * @param serialId the serial id
     * @return the list of ${@link Comment}
     * @throws DaoException if the method failed
     */
    List<Comment> findAllCommentForSerial(String serialId) throws DaoException;

}
