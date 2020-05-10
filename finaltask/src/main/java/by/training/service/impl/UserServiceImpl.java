package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.UserService;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.UserValidationImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;

/**
 * Implementation of {@link UserService} interface. Provides access to {@link by.training.dao.UserDao}
 * and provides support for working with the entity {@link User}.
 *
 * @see Transaction
 * @see DaoException
 */
public class UserServiceImpl implements UserService {

    /**
     * Validator for this Service.
     */
    private static final Validation<User> VALIDATOR = new UserValidationImpl();

    /**
     * Find user by login.
     *
     * @param login the user login
     * @return the user and null if does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public User findByLogin(final String login) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            User result = DaoFactory.getInstance().getUserDao(transaction).findByLogin(login);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return true if user was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final User user) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(user);
            if (errors.isEmpty()) {
                user.setPassword(ServiceFactory.getInstance()
                        .getSecurityService().hashpw(user.getPassword(), BCrypt.gensalt()));
                if (user.getId() == 0) {
                    result = DaoFactory.getInstance().getUserDao(transaction).create(user);
                } else {
                    result = DaoFactory.getInstance().getUserDao(transaction).update(user);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid data", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
