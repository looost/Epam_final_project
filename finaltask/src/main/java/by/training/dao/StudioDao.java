package by.training.dao;

import by.training.dao.exception.DaoException;
import by.training.model.Studio;

import java.util.List;

public interface StudioDao extends AbstractDao<String, Studio> {

    Studio findByName(String studioName) throws DaoException;

    List<Studio> findAll() throws DaoException;

    List<Studio> findStudioPageByPage(int page, int limit) throws DaoException;

    int countAllStudio() throws DaoException;
}
