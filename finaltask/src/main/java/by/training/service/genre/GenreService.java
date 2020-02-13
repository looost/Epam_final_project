package by.training.service.genre;

import by.training.model.Genre;
import by.training.service.AbstractService;
import by.training.service.exception.ServiceException;

public interface GenreService extends AbstractService {
    Genre findByName(String name) throws ServiceException;
}
