package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Entity;

import java.util.List;

public interface AbstractDao<KEY, ENTITY extends Entity> {

    List<ENTITY> findAll() throws DaoException; // TODO для жанров нужен не List а Set - жанры уникальны

    ENTITY findById(KEY id) throws DaoException;

    boolean delete(KEY id) throws DaoException;

    boolean create(ENTITY entity) throws DaoException;

    boolean update(ENTITY entity) throws DaoException;

}
