package by.training.service.impl;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.service.CommentService;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.TransactionHandler;
import by.training.service.transaction.TransactionHandlerFactory;
import by.training.service.transaction.TransactionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final TransactionHandler<Comment> COMMENT_TRANSACTION_HANDLER = TransactionHandlerFactory.COMMENT_TRANSACTION_HANDLER;

    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Comment> commentList = DaoFactory.getInstance().getCommentDao(connection).findAllCommentForSerial(serialId);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getListTransactionHandler(COMMENT_TRANSACTION_HANDLER), commentList);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment findById(String id) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            Comment comment = DaoFactory.getInstance().getCommentDao(connection).findById(id);
            return TransactionUtil
                    .select(connection, TransactionHandlerFactory.getSingleTransactionHandler(COMMENT_TRANSACTION_HANDLER), comment);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Comment entity) throws ServiceException {
//        Connection connection;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection = ConnectionPool.getInstance().getConnection();
            return DaoFactory.getInstance().getCommentDao(connection).create(entity);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Comment entity) throws ServiceException {
        return false;
    }

}
