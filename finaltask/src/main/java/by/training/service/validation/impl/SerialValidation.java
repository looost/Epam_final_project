package by.training.service.validation.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;

import javax.servlet.http.HttpServletResponse;

import static by.training.utils.ConstantName.*;

public class SerialValidation implements Validation<Serial> {

    @Override
    public boolean isValid(Transaction transaction, Serial serial) throws ServiceException {
        try {
            return serial.getName() != null && !serial.getName().equals("") &&
                    serial.getDescription() != null && !serial.getDescription().equals("") &&
                    serial.getLogo() != null && !serial.getLogo().equals("") &&
                    serial.getFullLogo() != null && !serial.getFullLogo().equals("") &&
                    serial.getCountry().getId() > 0 && serial.getStudio().getId() > 0 &&
                    (DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(serial.getName()) == null ||
                            DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(serial.getName()).getId() == serial.getId()) &&
                    serial.getDescription().length() <= MAX_SERIAL_DESCRIPTION_LENGTH &&
                    serial.getName().length() <= MAX_SERIAL_NAME_LENGTH &&
                    serial.getLogo().length() <= MAX_SERIAL_LOGO_LENGTH &&
                    serial.getFullLogo().length() <= MAX_SERIAL_FULL_LOGO_LENGTH;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
