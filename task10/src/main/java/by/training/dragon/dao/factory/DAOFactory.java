package by.training.dragon.dao.factory;

import by.training.dragon.dao.CaveDao;
import by.training.dragon.dao.impl.FileCaveDao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final CaveDao caveDao = new FileCaveDao();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public CaveDao getCaveDao() {
        return caveDao;
    }
}
