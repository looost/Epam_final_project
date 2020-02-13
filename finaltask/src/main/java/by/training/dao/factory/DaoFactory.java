package by.training.dao.factory;

import by.training.dao.genre.GenreDao;
import by.training.dao.genre.impl.GenreDaoImpl;
import by.training.dao.serial.SerialDao;
import by.training.dao.serial.impl.SerialDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final SerialDao serialDao = new SerialDaoImpl();
    private final GenreDao genreDao = new GenreDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public SerialDao getSerialDao() {
        return serialDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }
}
