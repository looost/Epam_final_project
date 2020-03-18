package by.training.service;

import by.training.model.Comment;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface CommentService extends AbstractService<String, Comment> {

    List<Comment> findAllCommentForSerial(String serialId) throws ServiceException;
}
