package by.training.service.validation;

import by.training.model.AbstractEntity;
import by.training.service.exception.ServiceException;

import java.util.Map;

/**
 * Interface for a Service layer validation.
 *
 * @param <T> the {@link AbstractEntity}
 */
@FunctionalInterface
public interface Validation<T extends AbstractEntity> {
    Map<String, String> isValid(T entity) throws ServiceException;
}
