package by.training.controller.validation.impl;

import by.training.controller.validation.Validation;
import by.training.model.Comment;
import by.training.service.exception.ServiceException;

import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_COMMENT_PROBLEM;
import static by.training.utils.ConstantName.MAX_COMMENT_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Comment}.
 */
public class CommentValidationImpl implements Validation<Comment> {
    @Override
    public boolean isValid(final Comment entity, final Map<String, String> errors) throws ServiceException {
        if (entity.getComment().equals("")) {
            errors.put(ATTRIBUTE_COMMENT_PROBLEM, "fillOutField");
            return false;
        }
        if (entity.getComment().length() > MAX_COMMENT_LENGTH) {
            errors.put(ATTRIBUTE_COMMENT_PROBLEM, "invalidCommentLength");
            return false;
        }
        return true;
    }
}
