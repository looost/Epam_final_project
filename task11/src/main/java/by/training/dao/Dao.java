package by.training.dao;

import by.training.dao.exception.DAOException;

public interface Dao {
    String readData() throws DAOException;
}
