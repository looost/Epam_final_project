package by.training.dao.factory;

import by.training.dao.Dao;
import by.training.dao.impl.FileDao;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private final static Dao dao = new FileDao("src//main//resources//test.txt");


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public Dao getDao() {
        return dao;
    }
}
