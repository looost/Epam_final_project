package by.training.service;

import by.training.model.Comment;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface CommentService {
    List<Comment> findAllCommentForSerial(String serialId) throws ServiceException;

    Comment findById(String id) throws ServiceException;

    boolean delete(String id) throws ServiceException;
    boolean save(Comment comment) throws ServiceException;
}
