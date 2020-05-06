package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.AbstractEntity;

/**
 * The interface presents basic methods for working with {@link AbstractEntity}.
 *
 * @param <KEY>    key used for search entity
 * @param <ENTITY> specific entity who implements this interface
 */
public interface AbstractDao<KEY, ENTITY extends AbstractEntity> {

    /**
     * Find entity by key.
     *
     * @param id the entity id
     * @return the entity
     * @throws DaoException if the method failed
     */
    ENTITY findById(KEY id) throws DaoException;

    /**
     * Delete entity by key.
     *
     * @param id the entity id
     * @return true if operation was made successfully and false otherwise
     * @throws DaoException if the method failed
     */
    boolean delete(KEY id) throws DaoException;

    /**
     * Create entity.
     *
     * @param entity the entity object to be created
     * @return true if operation was made successfully and false otherwise
     * @throws DaoException if the method failed
     */
    boolean create(ENTITY entity) throws DaoException;

    /**
     * Update entity.
     *
     * @param entity the entity object to be updated
     * @return true if operation was made successfully and false otherwise
     * @throws DaoException if the method failed
     */
    boolean update(ENTITY entity) throws DaoException;

}
