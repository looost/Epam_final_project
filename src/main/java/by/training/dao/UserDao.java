package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.User;

/**
 * Dao interface for {@link User}, all method throws {@link DaoException}.
 */
public interface UserDao extends AbstractDao<String, User> {
    /**
     * Find user by login.
     *
     * @param login the user login
     * @return the user
     * @throws DaoException if the method failed
     */
    User findByLogin(String login) throws DaoException;
}
