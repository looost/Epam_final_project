package by.training.service.validation;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

public class UserValidation implements Validation<User> {

    @Override
    public boolean isValid(Transaction transaction, User user) throws ServiceException {
        try {
            User validUser = DaoFactory.getInstance().getUserDao(transaction).findByLogin(user.getLogin());
            return validUser == null || validUser.getId() == user.getId();
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
