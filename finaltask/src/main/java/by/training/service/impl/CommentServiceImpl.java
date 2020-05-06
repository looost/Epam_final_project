package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.CommentService;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.TransactionBuilder;
import by.training.service.transaction.TransactionBuilderFactory;
import by.training.service.transaction.TransactionUtil;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.CommentValidation;

import java.util.List;

/**
 * Implementation of {@link CommentService} interface. Provides access to {@link by.training.dao.CommentDao}
 * and provides support for working with the entity {@link Comment}.
 *
 * @see Transaction
 * @see DaoException
 */
public class CommentServiceImpl implements CommentService {

    /**
     * Implementation of {@link TransactionBuilder} functional interface.
     * Needs for build {@link Comment} from different DAO classes.
     */
    private static final TransactionBuilder<Comment> COMMENT_TRANSACTION_HANDLER = new TransactionBuilder<Comment>() {
        @Override
        public Comment transaction(final Transaction t, final Comment entity) throws ServiceException {
            try {
                if (entity == null) {
                    throw new ServiceException(ServiceException.NOT_FOUND);
                }
                User user = DaoFactory.getInstance().getUserDao(t).findById(String.valueOf(entity.getUser().getId()));
                entity.setUser(user);
                Serial serial = DaoFactory.getInstance().getSerialDao(t).findById(String.valueOf(entity.getSerial().getId()));
                entity.setSerial(serial);
                return entity;
            } catch (DaoException e) {
                throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
            }
        }
    };

    /**
     * Validator for this Service.
     */
    private static final Validation<Comment> VALIDATOR = new CommentValidation();

    /**
     * Find all comment for serial.
     *
     * @param serialId the serial id
     * @return the {@link List} of ${@link Comment} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Comment> findAllCommentForSerial(final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Comment> commentList = DaoFactory.getInstance().getCommentDao(transaction).findAllCommentForSerial(serialId);
            List<Comment> result = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(COMMENT_TRANSACTION_HANDLER), commentList);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find by id comment.
     *
     * @param id the id
     * @return the comment
     * @throws ServiceException if there is an error on the DAO layer or comment does not exist (== null)
     */
    @Override
    public Comment findById(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Comment comment = DaoFactory.getInstance().getCommentDao(transaction).findById(id);
            Comment result = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(COMMENT_TRANSACTION_HANDLER), comment);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete comment by id.
     *
     * @param id the id
     * @return true if comment was made deleted and false otherwise
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getCommentDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save comment.
     *
     * @param comment the comment
     * @return true if comment was made saved and false otherwise
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Comment comment) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (VALIDATOR.isValid(transaction, comment)) {
                if (comment.getId() == 0) {
                    result = DaoFactory.getInstance().getCommentDao(transaction).create(comment);
                } else {
                    result = DaoFactory.getInstance().getCommentDao(transaction).update(comment);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid comment", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

}
