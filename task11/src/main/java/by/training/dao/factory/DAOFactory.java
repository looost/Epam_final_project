package by.training.dao.factory;

import by.training.dao.Dao;
import by.training.dao.impl.FileDao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private static final Dao dao = new FileDao("src//main//resources//text.txt");


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public Dao getDao() {
        return dao;
    }
}
