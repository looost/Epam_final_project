package by.training.service;

import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface SerialService extends AbstractService<String, Serial> {

    List<Serial> findAll() throws ServiceException;

    //Serial findSerialByName(String name) throws ServiceException;

    List<Serial> findAllSerial2(int page, int limit) throws ServiceException;

    List<Serial> latestSerial(int limit) throws ServiceException;

    int countAllSerial() throws ServiceException;

    List<Serial> findSerialBySearchForm(SearchForm searchForm) throws ServiceException;

    //List<Serial> findSerialByGenre(String genreId) throws ServiceException;

    boolean userWatchThisSerial(String userId, String serialId) throws ServiceException;

    boolean toWatchSerial(String userId, String serialId) throws ServiceException;

    boolean stopWatchSerial(String userId, String serialId) throws ServiceException;

    List<Serial> findSerialsThatIWatch(String userId) throws ServiceException;

}
