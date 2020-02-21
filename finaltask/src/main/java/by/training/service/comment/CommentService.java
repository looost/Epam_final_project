package by.training.service.comment;

import by.training.model.Comment;
import by.training.service.exception.ServiceException;

import java.util.Set;

public interface CommentService {

    Set<Comment> findAllCommentForSerial(String serialId) throws ServiceException;

    Comment findById(String id) throws ServiceException;

    boolean delete(String id) throws ServiceException;

    boolean create(Comment entity) throws ServiceException;

    boolean update(Comment entity) throws ServiceException;
}
