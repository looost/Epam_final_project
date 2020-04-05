package by.training.service.validation;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;

import java.sql.Connection;

public class Validation {
    public static boolean isCorrectUserLogin(Connection c, User user) throws DaoException {
        return DaoFactory.getInstance().getUserDao(c).findByLogin(user.getLogin()) == null;
    }

    public static boolean serialIsWatch(Connection c, String serialId, String userID) throws DaoException {
        return DaoFactory.getInstance().getSerialDao(c).serialIsWatchStatus(serialId, userID) == null;
    }
}
