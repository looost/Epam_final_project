package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.form.SearchForm;

import java.util.List;


public interface SerialDao extends AbstractDao<String, Serial> {

    List<Serial> findAll() throws DaoException;

    //Serial findSerialByName(String name) throws DaoException;

    List<Serial> findSerialPageByPage(int page, int limit) throws DaoException;

    List<Serial> latestSerial(int limit) throws DaoException;

    int countAllSerials() throws DaoException;

    List<Serial> findSerialBySearchForm(SearchForm searchForm) throws DaoException;

    //List<Serial> findSerialByGenre(String genreId) throws DaoException;

    boolean createSerialGenre(int serialId, List<Genre> genres) throws DaoException;

    int createAndReturnIndex(Serial serial) throws DaoException;

    boolean toWatchSerial(String userId, String serialId) throws DaoException;

    boolean stopWatchSerial(String userId, String serialId) throws DaoException;

    Serial userWatchThisSerial(String serialId, String userId) throws DaoException;

    List<Serial> findSerialsThatIWatch(String userId) throws DaoException;
}
