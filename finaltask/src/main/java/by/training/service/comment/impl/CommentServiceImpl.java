package by.training.service.comment.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.comment.CommentService;
import by.training.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            List<Comment> commentSet = DaoFactory.getInstance().getCommentDao(connection).findAllCommentForSerial(serialId);
            User user;
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById(serialId);
            for (Comment c : commentSet
            ) {
                user = DaoFactory.getInstance().getUserDao(connection).findById(String.valueOf(c.getUser().getId()));
                c.setUser(user);
                c.setSerial(serial);
            }
            connection.commit();
            ConnectionPool.getInstance().close(connection);
            return commentSet;
        } catch (SQLException e) {
            throw new ServiceException("Commit problem", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment findById(String id) throws ServiceException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            Comment comment = DaoFactory.getInstance().getCommentDao(connection).findById(id);
            User user = DaoFactory.getInstance().getUserDao(connection).findById(String.valueOf(comment.getUser().getId()));
            comment.setUser(user);
            Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById(String.valueOf(comment.getSerial().getId()));
            comment.setSerial(serial);
            connection.commit();

            return comment;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            throw new ServiceException("Commit problem", e);
        } finally {
            try {
                ConnectionPool.getInstance().close(connection);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Comment entity) throws ServiceException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            DaoFactory.getInstance().getCommentDao(connection).create(entity);
            connection.commit();
            ConnectionPool.getInstance().close(connection);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Comment entity) throws ServiceException {
        return false;
    }

}
