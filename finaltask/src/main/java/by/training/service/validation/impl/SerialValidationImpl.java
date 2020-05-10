package by.training.service.validation.impl;

import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.*;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Serial}.
 */
public class SerialValidationImpl implements Validation<Serial> {
    /**
     * Validation for {@link Serial}.
     */
    @Override
    public Map<String, String> isValid(final Serial serial) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (serial.getName() == null || serial.getName().equals("")) {
            errors.put(ATTRIBUTE_SERIAL_NAME_PROBLEM, "fillOutField");
        }
        if (serial.getName() != null && serial.getName().length() > MAX_SERIAL_NAME_LENGTH) {
            errors.put(ATTRIBUTE_SERIAL_NAME_PROBLEM, "incorrectSerialNameLength");
        }
        if (serial.getDescription() == null || serial.getDescription().equals("")) {
            errors.put(ATTRIBUTE_SERIAL_DESCRIPTION_PROBLEM, "fillOutField");
        }
        if (serial.getDescription() != null && serial.getDescription().length() > MAX_SERIAL_DESCRIPTION_LENGTH) {
            errors.put(ATTRIBUTE_SERIAL_DESCRIPTION_PROBLEM, "incorrectDescriptionLength");
        }
        if (serial.getLogo().length() > MAX_SERIAL_LOGO_LENGTH) {
            errors.put(ATTRIBUTE_SERIAL_LOGO_PROBLEM, "incorrectImageNameLength");
        }
        if (serial.getFullLogo().length() > MAX_SERIAL_FULL_LOGO_LENGTH) {
            errors.put(ATTRIBUTE_SERIAL_FULL_LOGO_PROBLEM, "incorrectImageNameLength");
        }
        try {
            Serial searchedSerial = ServiceFactory.getInstance().getSerialService().findByName(serial.getName());
            if (searchedSerial != null && searchedSerial.getId() != serial.getId()) {
                errors.put(ATTRIBUTE_SERIAL_NAME_PROBLEM, "incorrectSerialName");
            }
        } catch (ServiceException ignored) {

        }
        return errors;
    }
}
