package by.training.service.validation.impl;

import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_GENRE_PROBLEM;
import static by.training.utils.ConstantName.MAX_GENRE_NAME_LENGTH;

/**
 * Implementation of {@link ValidationOld} interface.
 * Validation for {@link Genre}.
 */
public class GenreValidationImpl implements Validation<Genre> {

    /**
     * Validation for {@link Genre}.
     */
    @Override
    public Map<String, String> isValid(final Genre entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getGenreService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "incorrectGenreName");
            return errors;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getName().length() > MAX_GENRE_NAME_LENGTH) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "incorrectNameLength");
            return errors;
        }
        return errors;
    }
}
