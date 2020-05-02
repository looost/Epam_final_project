package by.training.service;

import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface SerialService {

    List<Serial> findAll() throws ServiceException;

    Serial findById(String id) throws ServiceException;

    List<Serial> findSerialPageByPage(int page, int limit) throws ServiceException;

    List<Serial> findMostLikedSerial(int page, int limit) throws ServiceException;

    List<Serial> latestSerial(int limit) throws ServiceException;

    int countAllSerial() throws ServiceException;

    List<Serial> findSerialBySearchForm(SearchForm searchForm, int page, int limit) throws ServiceException;

    int countAllSerialsBySearchForm(SearchForm searchForm) throws ServiceException;

    //List<Serial> findSerialByGenre(String genreId) throws ServiceException;

    boolean userWatchThisSerial(String userId, String serialId) throws ServiceException;

    boolean toWatchSerial(String userId, String serialId) throws ServiceException;

    boolean stopWatchSerial(String userId, String serialId) throws ServiceException;

    List<Serial> findSerialsThatIWatch(String userId, int page, int limit) throws ServiceException;

    int countAllSerialsThatIWatch(String userId) throws ServiceException;

    List<Serial> findSerialsThatILiked(String userId, int page, int limit) throws ServiceException;

    int countAllSerialsThatILiked(String userId) throws ServiceException;

    boolean userLikedThisSerial(String userId, String serialId) throws ServiceException;

    boolean likeSerial(String userId, String serialId) throws ServiceException;

    boolean dislikeSerial(String userId, String serialId) throws ServiceException;

    boolean delete(String id) throws ServiceException;

    boolean save(Serial serial) throws ServiceException;

}
