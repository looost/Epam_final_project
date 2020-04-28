package by.training.service.validation;

import by.training.dao.Transaction;
import by.training.model.AbstractEntity;
import by.training.service.exception.ServiceException;

public interface Validation<T extends AbstractEntity> {
    boolean isValid(Transaction transaction, T entity) throws ServiceException;
}
