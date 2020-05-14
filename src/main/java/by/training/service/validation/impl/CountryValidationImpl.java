package by.training.service.validation.impl;

import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_COUNTRY_PROBLEM;
import static by.training.utils.ConstantName.MAX_COUNTRY_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Country}.
 */
public class CountryValidationImpl implements Validation<Country> {

    /**
     * Validation for {@link Country}.
     */
    @Override
    public Map<String, String> isValid(final Country entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getCountryService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "incorrectCountryName");
            return errors;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getName().length() > MAX_COUNTRY_NAME_LENGTH) {
            errors.put(ATTRIBUTE_COUNTRY_PROBLEM, "incorrectNameLength");
            return errors;
        }
        return errors;
    }
}
