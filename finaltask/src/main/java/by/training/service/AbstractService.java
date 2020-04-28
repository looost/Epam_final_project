package by.training.service;

import by.training.model.AbstractEntity;
import by.training.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface AbstractService<KEY, ENTITY extends AbstractEntity> {

    ENTITY findById(KEY id) throws ServiceException;

    boolean delete(KEY id) throws ServiceException;

    boolean create(ENTITY entity) throws ServiceException;

    boolean update(ENTITY entity) throws ServiceException;

}
