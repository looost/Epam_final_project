package by.training.controller.validation.impl;

import by.training.controller.validation.Validation;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_STUDIO_PROBLEM;
import static by.training.utils.ConstantName.MAX_STUDIO_NAME_LENGTH;


/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Studio}.
 */
public class StudioValidationImpl implements Validation<Studio> {

    @Override
    public boolean isValid(final Studio entity, final Map<String, String> errors) throws ServiceException {
        if (ServiceFactory.getInstance().getStudioService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "incorrectStudioName");
            return false;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "fillOutField");
            return false;
        }
        if (entity.getName().length() > MAX_STUDIO_NAME_LENGTH) {
            errors.put(ATTRIBUTE_STUDIO_PROBLEM, "incorrectNameLength");
            return false;
        }
        return true;
    }
}
