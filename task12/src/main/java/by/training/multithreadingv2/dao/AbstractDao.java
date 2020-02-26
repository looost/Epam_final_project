package by.training.multithreadingv2.dao;

import by.training.multithreadingv2.dao.exception.DaoException;

public interface AbstractDao {
    String readData() throws DaoException;
}
