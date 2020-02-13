package by.training.dao.serial;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.Serial;


public interface SerialDao extends AbstractDao<String, Serial> {

    Serial findSerialByName(String name) throws DaoException;
    // TODO добавить метод на проверку существует ли?
}
