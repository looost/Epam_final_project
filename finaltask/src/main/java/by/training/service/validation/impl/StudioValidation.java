package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;

import static by.training.utils.ConstantName.MAX_STUDIO_NAME_LENGTH;

public class StudioValidation implements Validation<Studio> {

    @Override
    public boolean isValid(Transaction transaction, Studio entity) throws ServiceException {
        try {
            return DaoFactory.getInstance()
                    .getStudioDao(transaction)
                    .findByName(entity.getName()) == null &&
                    !entity.getName().equals("") &&
                    entity.getName().length() <= MAX_STUDIO_NAME_LENGTH;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
