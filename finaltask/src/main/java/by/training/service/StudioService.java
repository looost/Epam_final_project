package by.training.service;

import by.training.model.Studio;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface StudioService {
    List<Studio> findAll() throws ServiceException;

    Studio findByName(String studioName) throws ServiceException;

    List<Studio> findStudioPageByPage(int page, int limit) throws ServiceException;

    int countAllStudio() throws ServiceException;

    boolean delete(String id) throws ServiceException;

    boolean save(Studio studio) throws ServiceException;
}
