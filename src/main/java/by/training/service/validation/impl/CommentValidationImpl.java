package by.training.service.validation.impl;

import by.training.model.Comment;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_COMMENT_PROBLEM;
import static by.training.utils.ConstantName.MAX_COMMENT_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Comment}.
 */
public class CommentValidationImpl implements Validation<Comment> {

    /**
     * Validation for {@link Comment}.
     */
    @Override
    public Map<String, String> isValid(final Comment entity) {
        Map<String, String> errors = new HashMap<>();
        if (entity.getComment().equals("")) {
            errors.put(ATTRIBUTE_COMMENT_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getComment().length() > MAX_COMMENT_LENGTH) {
            errors.put(ATTRIBUTE_COMMENT_PROBLEM, "invalidCommentLength");
            return errors;
        }
        return errors;
    }
}
