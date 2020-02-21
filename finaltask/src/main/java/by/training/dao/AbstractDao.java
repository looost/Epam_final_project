package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.AbstractEntity;

import java.util.List;

public interface AbstractDao<KEY, ENTITY extends AbstractEntity> {

    List<ENTITY> findAll() throws DaoException; // TODO для жанров нужен не List а Set - жанры уникальны

    ENTITY findById(KEY id) throws DaoException;

    boolean delete(KEY id) throws DaoException;

    boolean create(ENTITY entity) throws DaoException;

    boolean update(ENTITY entity) throws DaoException;

}
