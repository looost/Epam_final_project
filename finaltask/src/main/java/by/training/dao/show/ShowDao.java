package by.training.dao.show;

import by.training.dao.AbstractDao;
import by.training.dao.exception.DaoException;
import by.training.entity.Show;


public interface ShowDao extends AbstractDao<String, Show> {

    Show findShowByName(String name) throws DaoException;
    // TODO добавить метод на проверку существует ли?
}
