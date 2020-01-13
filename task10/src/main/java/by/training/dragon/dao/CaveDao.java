package by.training.dragon.dao;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.entity.Cave;

public interface CaveDao {
    Cave getCave() throws DAOException;
}
