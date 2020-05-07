package by.training.controller.validation;

import by.training.model.AbstractEntity;
import by.training.service.exception.ServiceException;

import java.util.Map;

@FunctionalInterface
public interface Validation<T extends AbstractEntity> {
    boolean isValid(T entity, Map<String, String> errors) throws ServiceException;
}
