package by.training.multithreadingv2.dao.factory;

import by.training.multithreadingv2.dao.AbstractDao;
import by.training.multithreadingv2.dao.impl.FileDao;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();
    private final AbstractDao dao = new FileDao("src\\main\\java\\by\\training\\multithreadingv2\\data\\matrix.txt");

    public static DaoFactory getInstance() {
        return instance;
    }

    public AbstractDao getDao() {
        return dao;
    }
}
