package by.training.service.serial;

import by.training.model.Serial;
import by.training.service.AbstractService;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface SerialService extends AbstractService<String, Serial> {
    Serial findSerialByName(String name) throws ServiceException;

    List<Serial> findAllSerial2(int page, int limit) throws ServiceException;

    List<Serial> findSerialBySearchForm(String searchQuery) throws ServiceException;
}
