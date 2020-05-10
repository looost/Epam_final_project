package by.training.service;

import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;

import java.util.List;

/**
 * The service interface for {@link Serial}, all method throws {@link ServiceException}.
 */
public interface SerialService {

    /**
     * Find all serial.
     *
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> findAll() throws ServiceException;

    /**
     * Find serial by id.
     *
     * @param id the serial id
     * @return the serial
     * @throws ServiceException if the method failed
     */
    Serial findById(String id) throws ServiceException;

    /**
     * Find serial by name.
     *
     * @param name the serial name
     * @return the serial
     * @throws ServiceException if the method failed
     */
    Serial findByName(String name) throws ServiceException;

    /**
     * Find serial page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> findSerialPageByPage(int page, int limit) throws ServiceException;

    /**
     * Find most liked serial.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> findMostLikedSerial(int page, int limit) throws ServiceException;

    /**
     * Latest serial.
     *
     * @param limit the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> latestSerial(int limit) throws ServiceException;

    /**
     * Count of all serials.
     *
     * @return number of all serials
     * @throws ServiceException if the method failed
     */
    int countAllSerial() throws ServiceException;

    /**
     * Find serial by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     * @see SearchForm
     */
    List<Serial> findSerialBySearchForm(SearchForm searchForm, int page, int limit) throws ServiceException;

    /**
     * Count of all serials by search form.
     *
     * @param searchForm the search form
     * @return number of all serials by search form
     * @throws ServiceException if the method failed
     * @see SearchForm
     */
    int countAllSerialsBySearchForm(SearchForm searchForm) throws ServiceException;

    /**
     * Check if the user is watching this serial.
     *
     * @param serialId the serial id
     * @param userId   the user id
     * @return true if user watch this serial and false otherwise.
     * @throws ServiceException if the method failed
     */
    boolean userWatchThisSerial(String userId, String serialId) throws ServiceException;

    /**
     * To watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if the method failed
     */
    void toWatchSerial(String userId, String serialId) throws ServiceException;

    /**
     * Stop watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if the method failed
     */
    void stopWatchSerial(String userId, String serialId) throws ServiceException;

    /**
     * Find serials that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> findSerialsThatUserWatch(String userId, int page, int limit) throws ServiceException;

    /**
     * Count of all serials that user watch.
     *
     * @param userId the user id
     * @return number of serials that is watch user
     * @throws ServiceException if the method failed
     */
    int countAllSerialsThatUserWatch(String userId) throws ServiceException;

    /**
     * Find serials that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of serials
     * @throws ServiceException if the method failed
     */
    List<Serial> findSerialsThatUserLiked(String userId, int page, int limit) throws ServiceException;

    /**
     * Count of all serials that user liked.
     *
     * @param userId the user id
     * @return number of serials that liked user
     * @throws ServiceException if the method failed
     */
    int countAllSerialsThatUserLiked(String userId) throws ServiceException;

    /**
     * Check whether the user likes this serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return true if user liked this serial and false otherwise.
     * @throws ServiceException if the method failed
     */
    boolean userLikedThisSerial(String userId, String serialId) throws ServiceException;

    /**
     * Add a serial to user liked.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if the method failed
     */
    void likeSerial(String userId, String serialId) throws ServiceException;

    /**
     * Remove a serial to user liked.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if the method failed
     */
    void dislikeSerial(String userId, String serialId) throws ServiceException;

    /**
     * Delete serial.
     *
     * @param id the id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save serial.
     *
     * @param serial the serial
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Serial serial) throws ServiceException;

}
