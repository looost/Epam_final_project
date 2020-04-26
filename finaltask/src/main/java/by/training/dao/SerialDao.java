package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.form.SearchForm;

import java.util.List;


public interface SerialDao extends AbstractDao<String, Serial> {

    List<Serial> findAll() throws DaoException;

    Serial findSerialByName(String name) throws DaoException;

    List<Serial> findSerialPageByPage(int page, int limit) throws DaoException;

    List<Serial> findMostLikedSerial(int page, int limit) throws DaoException;

    List<Serial> latestSerial(int limit) throws DaoException;

    int countAllSerials() throws DaoException;

    List<Serial> findSerialBySearchForm(SearchForm searchForm) throws DaoException;

    //List<Serial> findSerialByGenre(String genreId) throws DaoException;

    boolean toWatchSerial(String userId, String serialId) throws DaoException;

    boolean stopWatchSerial(String userId, String serialId) throws DaoException;

    boolean userWatchThisSerial(String serialId, String userId) throws DaoException;

    List<Serial> findSerialsThatIWatch(String userId, int page, int limit) throws DaoException;

    int countAllSerialsThatIWatch(String userId) throws DaoException;

    List<Serial> findSerialsThatILiked(String userId, int page, int limit) throws DaoException;

    int countAllSerialsThatILiked(String userId) throws DaoException;

    boolean likeSerial(String userId, String serialId) throws DaoException;

    boolean dislikeSerial(String userId, String serialId) throws DaoException;

    boolean userLikedThisSerial(String userId, String serialId) throws DaoException;

}
