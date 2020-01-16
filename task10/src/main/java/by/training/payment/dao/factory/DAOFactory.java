package by.training.payment.dao.factory;

import by.training.payment.dao.Dao;
import by.training.payment.dao.impl.FileDao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final Dao dao = new FileDao("src\\main\\resources\\Product.txt");

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public Dao getDao() {
        return dao;
    }
}
