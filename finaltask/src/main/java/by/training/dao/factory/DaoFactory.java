package by.training.dao.factory;

import by.training.dao.*;
import by.training.dao.impl.*;

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

//    public CountryDaoImpl getCountryDao() {
//        return new CountryDaoImpl();
//    }

    public StudioDaoImpl getStudioDao(Connection connection) {
        return new StudioDaoImpl(connection);
    }

    public CommentDao getCommentDao(Connection connection) {
        return new CommentDaoImpl(connection);
    }
}
