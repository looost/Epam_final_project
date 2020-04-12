package by.training.service;

import by.training.model.Studio;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface StudioService extends AbstractService<String, Studio> {
    List<Studio> findAll() throws ServiceException;

    List<Studio> findStudioPageByPage(int page, int limit) throws ServiceException;

    int countAllStudio() throws ServiceException;
}
