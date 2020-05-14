package by.training.service;

import by.training.model.Genre;
import by.training.service.exception.ServiceException;

import java.util.List;

/**
 * The service interface for {@link Genre}, all method throws {@link ServiceException}.
 */
public interface GenreService {
    /**
     * Find all genres.
     *
     * @return the list of genres
     * @throws ServiceException if the method failed
     */
    List<Genre> findAll() throws ServiceException;

    /**
     * Find genre page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of genres
     * @throws ServiceException if the method failed
     */
    List<Genre> findGenrePageByPage(int page, int limit) throws ServiceException;

    /**
     * Count all genres.
     *
     * @return number of all genres
     * @throws ServiceException if the method failed
     */
    int countAllGenres() throws ServiceException;

    /**
     * Find genre by name.
     *
     * @param name the genre name
     * @return the genre
     * @throws ServiceException if the method failed
     */
    Genre findByName(String name) throws ServiceException;

    /**
     * Delete genre.
     *
     * @param id the genre id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save genre.
     *
     * @param genre the genre
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Genre genre) throws ServiceException;
}
