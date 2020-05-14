package by.training.service;

import by.training.model.Studio;
import by.training.service.exception.ServiceException;

import java.util.List;

/**
 * The service interface for {@link Studio}, all method throws {@link ServiceException}.
 */
public interface StudioService {
    /**
     * Find all studios.
     *
     * @return the list of studios
     * @throws ServiceException if the method failed
     */
    List<Studio> findAll() throws ServiceException;

    /**
     * Find studio by name.
     *
     * @param studioName the studio name
     * @return the studio
     * @throws ServiceException if the method failed
     */
    Studio findByName(String studioName) throws ServiceException;

    /**
     * Find studio page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the list of genres
     * @throws ServiceException if the method failed
     */
    List<Studio> findStudioPageByPage(int page, int limit) throws ServiceException;

    /**
     * Count of all studio.
     *
     * @return number of all studios
     * @throws ServiceException if the method failed
     */
    int countAllStudio() throws ServiceException;

    /**
     * Delete studio.
     *
     * @param id the id
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean delete(String id) throws ServiceException;

    /**
     * Save studio.
     *
     * @param studio the studio
     * @return true if operation was made successfully and false otherwise
     * @throws ServiceException if the method failed
     */
    boolean save(Studio studio) throws ServiceException;
}
