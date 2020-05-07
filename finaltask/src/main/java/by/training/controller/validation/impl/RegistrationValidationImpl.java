package by.training.controller.validation.impl;

import by.training.controller.validation.Validation;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_INVALID_LOGIN;
import static by.training.utils.ConstantName.MAX_USER_LOGIN_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * {@link User} registration validation.
 */
public class RegistrationValidationImpl implements Validation<User> {
    @Override
    public boolean isValid(final User entity, final Map<String, String> errors) throws ServiceException {
        User user = ServiceFactory.getInstance().getUserService().findByLogin(entity.getLogin());
        if (user != null && user.getId() != entity.getId()) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "incorrectLogin");
            return false;
        }
        if (entity.getLogin().length() > MAX_USER_LOGIN_LENGTH) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "invalidLoginLength");
            return false;
        }
        return true;
    }
}
