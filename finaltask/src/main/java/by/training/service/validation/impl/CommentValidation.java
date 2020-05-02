package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.model.Comment;
import by.training.service.validation.Validation;

import static by.training.utils.ConstantName.MAX_COMMENT_LENGTH;

public class CommentValidation implements Validation<Comment> {

    @Override
    public boolean isValid(Transaction transaction, Comment entity) {
        return !entity.getComment().equals("") && entity.getComment().length() <= MAX_COMMENT_LENGTH;
    }
}
