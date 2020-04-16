package by.training.service.validation;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;

public class Validation {

    private Validation() {
    }

    public static boolean isCorrectUserLogin(Transaction t, User user) throws DaoException {
        return DaoFactory.getInstance().getUserDao(t).findByLogin(user.getLogin()) == null;
    }

    public static boolean serialIsWatch(Transaction t, String serialId, String userID) throws DaoException {
        return DaoFactory.getInstance().getSerialDao(t).userWatchThisSerial(serialId, userID) == null;
    }
}
