package by.training.service;

import by.training.service.exception.ServiceException;

public interface ViewedService {
    boolean create(String userId, String serialId) throws ServiceException;

    boolean delete(String userId, String serialId) throws ServiceException;
}
