package by.training.multithreading.dao.factory;

import by.training.multithreading.dao.AbstractDao;
import by.training.multithreading.dao.impl.FileDao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final AbstractDao dao = new FileDao();

    public static DaoFactory getInstance() {
        return instance;
    }

    public AbstractDao getDao() {
        return dao;
    }
}
