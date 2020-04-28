package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.UserService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.UserValidation;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;


public class UserServiceImpl implements UserService {

    private static final Validation<User> validation = new UserValidation();

    @Override
    public User findByLogin(String login) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            User result = DaoFactory.getInstance().getUserDao(transaction).findByLogin(login);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean createUserWithRole(User user) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (validation.isValid(transaction, user)) {
                DaoFactory.getInstance().getUserDao(transaction).createUserWithRole(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User findById(String id) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(User entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (validation.isValid(transaction, entity)) {
                DaoFactory.getInstance().getUserDao(transaction).create(entity);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            DaoFactory.getInstance().getUserDao(transaction).update(entity);
            transaction.commit();
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(User user) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validation.isValid(transaction, user)) {
                if (user.getId() == 0) {
                    result = DaoFactory.getInstance().getUserDao(transaction).create(user);
                } else {
                    result = DaoFactory.getInstance().getUserDao(transaction).update(user);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid data", HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
