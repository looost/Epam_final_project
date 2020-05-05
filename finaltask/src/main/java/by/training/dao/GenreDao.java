package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Genre;

import java.util.List;

/**
 * Dao interface for {@link Genre}, all method throws {@link DaoException}.
 */
public interface GenreDao extends AbstractDao<String, Genre> {
    /**
     * Find all genre.
     *
     * @return the list of genres
     * @throws DaoException if the method failed
     */
    List<Genre> findAll() throws DaoException;

    /**
     * Find genre page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of genres
     * @throws DaoException if the method failed
     */
    List<Genre> findGenrePageByPage(int page, int limit) throws DaoException;

    /**
     * Count all genres.
     *
     * @return number of all genres
     * @throws DaoException if the method failed
     */
    int countAllGenres() throws DaoException;

    /**
     * Find genre by name.
     *
     * @param name the genre name
     * @return the genre
     * @throws DaoException if the method failed
     */
    Genre findByName(String name) throws DaoException;

    /**
     * Find genres by serial id.
     *
     * @param serialId the serial id
     * @return the list of genres
     * @throws DaoException if the method failed
     * @see by.training.model.Serial
     */
    List<Genre> findGenreBySerialId(String serialId) throws DaoException;
}
