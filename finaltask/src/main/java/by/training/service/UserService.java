package by.training.service;

import by.training.model.User;
import by.training.service.exception.ServiceException;

import java.io.InputStream;

public interface UserService extends AbstractService<String, User> {
    User findByLogin(String login) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    boolean createUserWithRole(User user) throws ServiceException;
}
