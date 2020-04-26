package by.training.service.validation;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;

import static by.training.utils.ConstantName.MAX_SERIAL_DESCRIPTION_LENGTH;

public class SerialValidation {

    public boolean isValidSerial(Transaction transaction, Serial serial) throws DaoException {
        return  serial.getName() != null && serial.getDescription() != null && serial.getLogo() != null &&
                serial.getFullLogo() != null &&
                (DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(serial.getName()) == null ||
                        DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(serial.getName()).getId() == serial.getId()) &&
                serial.getDescription().length() <= MAX_SERIAL_DESCRIPTION_LENGTH;
    }
}
