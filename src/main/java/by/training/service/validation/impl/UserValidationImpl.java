package by.training.service.validation.impl;

import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_INVALID_LOGIN;
import static by.training.utils.ConstantName.MAX_USER_LOGIN_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link User}.
 */
public class UserValidationImpl implements Validation<User> {

    @Override
    public Map<String, String> isValid(final User entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        User user = ServiceFactory.getInstance().getUserService().findByLogin(entity.getLogin());
        if (user != null && user.getId() != entity.getId()) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "incorrectLogin");
            return errors;
        }
        if (entity.getLogin().length() > MAX_USER_LOGIN_LENGTH) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "invalidLoginLength");
            return errors;
        }
        return errors;
    }
}
