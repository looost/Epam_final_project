package by.training.dao.serial;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.model.Serial;

import java.util.List;


public interface SerialDao extends AbstractDao<String, Serial> {

    Serial findSerialByName(String name) throws DaoException;

    List<Serial> findAllSerial2(int page, int limit) throws DaoException;

    List<Serial> findSerialBySearchForm(String searchQuery) throws DaoException;

}
