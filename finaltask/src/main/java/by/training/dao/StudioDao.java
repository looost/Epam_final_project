package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Studio;

import java.util.List;

public interface StudioDao extends AbstractDao<String, Studio> {
    List<Studio> findAll() throws DaoException;
}
