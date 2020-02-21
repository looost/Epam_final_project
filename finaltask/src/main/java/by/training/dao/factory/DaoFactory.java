package by.training.dao.factory;

import by.training.dao.comment.CommentDao;
import by.training.dao.comment.impl.CommentDaoImpl;
import by.training.dao.country.CountryDaoImpl;
import by.training.dao.genre.GenreDao;
import by.training.dao.genre.impl.GenreDaoImpl;
import by.training.dao.serial.SerialDao;
import by.training.dao.serial.impl.SerialDaoImpl;
import by.training.dao.studio.StudioDaoImpl;
import by.training.dao.user.UserDao;
import by.training.dao.user.impl.UserDaoImpl;

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
}
