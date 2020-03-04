package by.training.multithreading.dao;

import by.training.multithreading.dao.exception.DaoException;

public interface AbstractDao {
    String readData(String filePath) throws DaoException;
}
