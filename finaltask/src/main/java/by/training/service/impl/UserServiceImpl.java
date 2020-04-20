package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.model.User;
import by.training.service.UserService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;


public class UserServiceImpl implements UserService {

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
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            User result = DaoFactory.getInstance().getUserDao(transaction).findByLoginAndPassword(login, password);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean createUserWithRole(User user) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (Validation.isCorrectUserLogin(transaction, user)) {
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
            if (Validation.isCorrectUserLogin(transaction, entity)) {
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

}
