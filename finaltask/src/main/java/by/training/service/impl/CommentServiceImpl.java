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
        try (Transaction transaction = new Transaction()) {
            List<Comment> commentList = DaoFactory.getInstance().getCommentDao(transaction).findAllCommentForSerial(serialId);
            List<Comment> result = TransactionUtil
                    .select(transaction, TransactionHandlerFactory.getListTransactionHandler(COMMENT_TRANSACTION_HANDLER), commentList);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment findById(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Comment comment = DaoFactory.getInstance().getCommentDao(transaction).findById(id);
            Comment result = TransactionUtil
                    .select(transaction, TransactionHandlerFactory.getSingleTransactionHandler(COMMENT_TRANSACTION_HANDLER), comment);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCommentDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Comment entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean res = DaoFactory.getInstance().getCommentDao(transaction).create(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Comment entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean res = DaoFactory.getInstance().getCommentDao(transaction).update(entity);
            transaction.commit();
            return res;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
