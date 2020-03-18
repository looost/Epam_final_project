package by.training.dao.factory;

import by.training.dao.CommentDao;
import by.training.dao.impl.CommentDaoImpl;
import by.training.dao.impl.CountryDaoImpl;
import by.training.dao.GenreDao;
import by.training.dao.impl.GenreDaoImpl;
import by.training.dao.SerialDao;
import by.training.dao.impl.SerialDaoImpl;
import by.training.dao.impl.SerialGenreDao;
import by.training.dao.impl.StudioDaoImpl;
import by.training.dao.UserDao;
import by.training.dao.impl.UserDaoImpl;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao(Connection connection) { // TODO как сделать фабрику?
        return new UserDaoImpl(connection);
    }

    public SerialDao getSerialDao(Connection connection) {
        return new SerialDaoImpl(connection);
    }

    public GenreDao getGenreDao(Connection connection) {
        return new GenreDaoImpl(connection);
    }

    public CountryDaoImpl getCountryDao(Connection connection) {
        return new CountryDaoImpl(connection);
    }

    public StudioDaoImpl getStudioDao(Connection connection) {
        return new StudioDaoImpl(connection);
    }

    public CommentDao getCommentDao(Connection connection) {
        return new CommentDaoImpl(connection);
    }

    public SerialGenreDao getSerialGenreDao(Connection connection) {
        return new SerialGenreDao(connection);
    }
}
