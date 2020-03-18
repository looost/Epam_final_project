package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Comment;

import java.util.List;

public interface CommentDao extends AbstractDao<String, Comment> {

    List<Comment> findAllCommentForSerial(String serialId) throws DaoException;

}
