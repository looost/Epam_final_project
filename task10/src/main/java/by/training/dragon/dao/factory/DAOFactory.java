package by.training.dragon.dao.factory;

import by.training.dragon.dao.Dao;
import by.training.dragon.dao.impl.FileDao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final Dao dao = new FileDao("src\\main\\resources\\treasure.txt");

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public Dao getDao() {
        return dao;
    }
}
