package by.training.multithreading.dao.factory;

import by.training.multithreading.dao.AbstractDao;
import by.training.multithreading.dao.impl.FileDao;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();
    private final AbstractDao dao = new FileDao("src\\main\\java\\by\\training\\multithreading\\data\\matrix.txt");

    public static DaoFactory getInstance() {
        return instance;
    }

    public AbstractDao getDao() {
        return dao;
    }
}
