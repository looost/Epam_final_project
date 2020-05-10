package by.training.service.validation.impl;

import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_STUDIO_PROBLEM;
import static by.training.utils.ConstantName.MAX_STUDIO_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Studio}.
 */
public class StudioValidationImpl implements Validation<Studio> {

    /**
     * Validation for {@link Studio}.
     */
    @Override
    public Map<String, String> isValid(final Studio entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getStudioService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "incorrectStudioName");
            return errors;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getName().length() > MAX_STUDIO_NAME_LENGTH) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "incorrectNameLength");
            return errors;
        }
        return errors;
    }
}
