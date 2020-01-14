package by.training.dragon.dao;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.entity.Cave;

import java.util.List;

public interface Dao {
    List<String> readData() throws DAOException;
}
