package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Studio;

import java.util.List;

/**
 * Dao interface for {@link Studio}, all method throws {@link DaoException}.
 */
public interface StudioDao extends AbstractDao<String, Studio> {

    /**
     * Find studio by name.
     *
     * @param studioName the studio name
     * @return the studio
     * @throws DaoException if the method failed
     */
    Studio findByName(String studioName) throws DaoException;

    /**
     * Find all genres.
     *
     * @return the list of genres
     * @throws DaoException if the method failed
     */
    List<Studio> findAll() throws DaoException;

    /**
     * Find studio page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of genres
     * @throws DaoException if the method failed
     */
    List<Studio> findStudioPageByPage(int page, int limit) throws DaoException;

    /**
     * Count all studio.
     *
     * @return number of all studios
     * @throws DaoException if the method failed
     */
    int countAllStudio() throws DaoException;
}
