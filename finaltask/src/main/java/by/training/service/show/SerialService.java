package by.training.service.show;

import by.training.model.Serial;
import by.training.service.AbstractService;
import by.training.service.exception.ServiceException;

public interface SerialService extends AbstractService<String, Serial> {
    Serial findSerialByName(String name) throws ServiceException;
}
