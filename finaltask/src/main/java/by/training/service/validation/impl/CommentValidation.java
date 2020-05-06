package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.model.Comment;
import by.training.service.validation.Validation;

import static by.training.utils.ConstantName.MAX_COMMENT_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Comment}.
 */
public class CommentValidation implements Validation<Comment> {

    @Override
    public boolean isValid(final Transaction transaction, final Comment entity) {
        return !entity.getComment().equals("") && entity.getComment().length() <= MAX_COMMENT_LENGTH;
    }
}
