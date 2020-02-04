package by.training.dao;

import by.training.dao.exception.DAOException;

import java.util.List;

public interface Dao {
    String readData() throws DAOException;
}
