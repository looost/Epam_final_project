package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;

import static by.training.utils.ConstantName.MAX_USER_LOGIN_LENGTH;

public class UserValidation implements Validation<User> {

    @Override
    public boolean isValid(Transaction transaction, User user) throws ServiceException {
        try {
            User validUser = DaoFactory.getInstance().getUserDao(transaction).findByLogin(user.getLogin());
            return user.getLogin().length() <= MAX_USER_LOGIN_LENGTH &&
                    (validUser == null || validUser.getId() == user.getId());
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
