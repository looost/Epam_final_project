package by.training.payment.dao;

import by.training.payment.dao.exception.DAOException;

import java.util.List;

public interface Dao {
    List<String> readData() throws DAOException;
}
