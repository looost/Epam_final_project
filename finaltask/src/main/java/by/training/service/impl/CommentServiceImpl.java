package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.service.CommentService;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.TransactionBuilder;
import by.training.service.transaction.TransactionBuilderFactory;
import by.training.service.transaction.TransactionUtil;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.CommentValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class CommentServiceImpl implements CommentService {

    private static final TransactionBuilder<Comment> COMMENT_TRANSACTION_HANDLER = TransactionBuilderFactory.COMMENT_TRANSACTION_BUILDER;
    private static final Validation<Comment> validator = new CommentValidation();

    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Comment> commentList = DaoFactory.getInstance().getCommentDao(transaction).findAllCommentForSerial(serialId);
            List<Comment> result = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(COMMENT_TRANSACTION_HANDLER), commentList);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Comment findById(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Comment comment = DaoFactory.getInstance().getCommentDao(transaction).findById(id);
            Comment result = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(COMMENT_TRANSACTION_HANDLER), comment);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCommentDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(Comment comment) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validator.isValid(transaction, comment)) {
                if (comment.getId() == 0) {
                    result = DaoFactory.getInstance().getCommentDao(transaction).create(comment);
                } else {
                    result = DaoFactory.getInstance().getCommentDao(transaction).update(comment);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid comment", HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
