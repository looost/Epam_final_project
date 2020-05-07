package by.training.controller.validation.impl;

import by.training.controller.validation.Validation;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_COUNTRY_PROBLEM;
import static by.training.utils.ConstantName.MAX_COUNTRY_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Country}.
 */
public class CountryValidationImpl implements Validation<Country> {
    @Override
    public boolean isValid(final Country entity, final Map<String, String> errors) throws ServiceException {
        if (ServiceFactory.getInstance().getCountryService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "incorrectCountryName");
            return false;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "fillOutField");
            return false;
        }
        if (entity.getName().length() > MAX_COUNTRY_NAME_LENGTH) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "incorrectNameLength");
            return false;
        }
        return true;
    }
}
