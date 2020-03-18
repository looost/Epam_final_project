package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.AbstractEntity;

public interface AbstractDao<KEY, ENTITY extends AbstractEntity> {

    ENTITY findById(KEY id) throws DaoException;

    boolean delete(KEY id) throws DaoException;

    boolean create(ENTITY entity) throws DaoException;

    boolean update(ENTITY entity) throws DaoException;

}
