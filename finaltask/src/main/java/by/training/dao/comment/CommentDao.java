package by.training.dao.comment;

import by.training.dao.exception.DaoException;
import by.training.model.Comment;

import java.util.List;
import java.util.Set;

public interface CommentDao {

    List<Comment> findAllCommentForSerial(String serialId) throws DaoException;

    Comment findById(String id) throws DaoException;

    boolean delete(String id) throws DaoException;

    boolean create(Comment entity) throws DaoException;

    boolean update(Comment entity) throws DaoException;
}
