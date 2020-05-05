package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Serial;
import by.training.model.form.SearchForm;

import java.util.List;


/**
 * Dao interface for {@link Serial}, all method throws {@link DaoException}.
 */
public interface SerialDao extends AbstractDao<String, Serial> {

    /**
     * Find all serial.
     *
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> findAll() throws DaoException;

    /**
     * Find serial by serial name.
     *
     * @param name the name
     * @return the serial
     * @throws DaoException if the method failed
     */
    Serial findSerialByName(String name) throws DaoException;

    /**
     * Find serial page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> findSerialPageByPage(int page, int limit) throws DaoException;

    /**
     * Find most liked serial.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> findMostLikedSerial(int page, int limit) throws DaoException;

    /**
     * Latest serial.
     *
     * @param limit the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> latestSerial(int limit) throws DaoException;

    /**
     * Count all serials.
     *
     * @return number of all serials
     * @throws DaoException if the method failed
     */
    int countAllSerials() throws DaoException;

    /**
     * Find serial by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    List<Serial> findSerialBySearchForm(SearchForm searchForm, int page, int limit) throws DaoException;

    /**
     * Count all serials by search form.
     *
     * @param searchForm the search form
     * @return number of all serials by search form
     * @throws DaoException if the method failed
     * @see SearchForm
     */
    int countAllSerialsBySearchForm(SearchForm searchForm) throws DaoException;

    /**
     * To watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return the boolean
     * @throws DaoException if the method failed
     */
    boolean toWatchSerial(String userId, String serialId) throws DaoException;

    /**
     * Stop watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return the boolean
     * @throws DaoException if the method failed
     */
    boolean stopWatchSerial(String userId, String serialId) throws DaoException;

    /**
     * User watch this serial.
     *
     * @param serialId the serial id
     * @param userId   the user id
     * @return true if user watch this serial and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean userWatchThisSerial(String serialId, String userId) throws DaoException;

    /**
     * Find serials that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> findSerialsThatUserWatch(String userId, int page, int limit) throws DaoException;

    /**
     * Count all serials that user watch.
     *
     * @param userId the user id
     * @return number of serials that is watch user
     * @throws DaoException if the method failed
     */
    int countAllSerialsThatUserWatch(String userId) throws DaoException;

    /**
     * Find serials that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the list of serials
     * @throws DaoException if the method failed
     */
    List<Serial> findSerialsThatUserLiked(String userId, int page, int limit) throws DaoException;

    /**
     * Count all serials that user liked int.
     *
     * @param userId the user id
     * @return number of serials that liked user
     * @throws DaoException if the method failed
     */
    int countAllSerialsThatUserLiked(String userId) throws DaoException;

    /**
     * Add a serial to user liked
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean likeSerial(String userId, String serialId) throws DaoException;

    /**
     * Remove a serial to user liked
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return true if operation was made successfully and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean dislikeSerial(String userId, String serialId) throws DaoException;

    /**
     * Check whether the user likes this serial
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return true if user liked this serial and false otherwise.
     * @throws DaoException if the method failed
     */
    boolean userLikedThisSerial(String userId, String serialId) throws DaoException;

}
