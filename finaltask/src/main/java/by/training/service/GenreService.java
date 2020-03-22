package by.training.service;

import by.training.model.Genre;
import by.training.service.exception.ServiceException;

import java.util.List;

public interface GenreService extends AbstractService<String, Genre> {
    List<Genre> findAll() throws ServiceException;
    Genre findByName(String name) throws ServiceException;
}
