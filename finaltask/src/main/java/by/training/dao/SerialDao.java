package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Serial;

import java.util.List;


public interface SerialDao extends AbstractDao<String, Serial> {

    List<Serial> findAll() throws DaoException;

    Serial findSerialByName(String name) throws DaoException;

    List<Serial> findAllSerial2(int page, int limit) throws DaoException;

    List<Serial> findSerialBySearchForm(String searchQuery) throws DaoException;

    boolean createSerialGenre(Serial serial) throws DaoException;

}
