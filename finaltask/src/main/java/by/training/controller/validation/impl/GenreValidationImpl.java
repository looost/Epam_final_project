package by.training.controller.validation.impl;

import by.training.controller.validation.Validation;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_GENRE_PROBLEM;
import static by.training.utils.ConstantName.MAX_GENRE_NAME_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Genre}.
 */
public class GenreValidationImpl implements Validation<Genre> {

    @Override
    public boolean isValid(final Genre entity, final Map<String, String> errors) throws ServiceException {
        if (ServiceFactory.getInstance().getGenreService().findByName(entity.getName()) != null) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "incorrectGenreName");
            return false;
        }
        if (entity.getName().equals("")) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "fillOutField");
            return false;
        }
        if (entity.getName().length() > MAX_GENRE_NAME_LENGTH) {
            errors.put(ATTRIBUTE_GENRE_PROBLEM, "incorrectNameLength");
            return false;
        }
        return true;
    }
}
