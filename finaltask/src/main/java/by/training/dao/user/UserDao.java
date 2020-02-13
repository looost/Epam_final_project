package by.training.dao.user;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.User;

public interface UserDao extends AbstractDao<String, User> {
    User findByLogin(String login) throws DaoException;
}
