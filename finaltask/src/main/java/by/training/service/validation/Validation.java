package by.training.service.validation;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;

import java.sql.Connection;

public class Validation {

    public static boolean isCorrectUserLogin(Connection c, User user) throws DaoException {
        return DaoFactory.getInstance().getUserDao(c).findByLogin(user.getLogin()) == null;
    }
}
