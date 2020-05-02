package by.training.service;

import by.training.model.User;
import by.training.service.exception.ServiceException;

public interface UserService {
    User findByLogin(String login) throws ServiceException;

    boolean createUserWithRole(User user) throws ServiceException;

    boolean save(User user) throws ServiceException;
}
