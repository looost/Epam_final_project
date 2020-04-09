package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.service.CommentService;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.TransactionHandler;
import by.training.service.transaction.TransactionHandlerFactory;
import by.training.service.transaction.TransactionUtil;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final TransactionHandler<Comment> COMMENT_TRANSACTION_HANDLER = TransactionHandlerFactory.COMMENT_TRANSACTION_HANDLER;

    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws ServiceException {
//        Connection connection;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            List<Comment> commentList = DaoFactory.getInstance().getCommentDao(transaction).findAllCommentForSerial(serialId);
            return TransactionUtil
                    .select(transaction, TransactionHandlerFactory.getListTransactionHandler(COMMENT_TRANSACTION_HANDLER), commentList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment findById(String id) throws ServiceException {
//        Connection connection;
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            Comment comment = DaoFactory.getInstance().getCommentDao(transaction).findById(id);
            return TransactionUtil
                    .select(transaction, TransactionHandlerFactory.getSingleTransactionHandler(COMMENT_TRANSACTION_HANDLER), comment);
        } catch (DaoException e) {
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
        try (Transaction transaction = new Transaction()) {
//            connection = ConnectionPool.getInstance().getConnection();
            boolean res = DaoFactory.getInstance().getCommentDao(transaction).create(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Comment entity) throws ServiceException {
        return false;
    }

}
