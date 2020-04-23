package by.training.dao.factory;

import by.training.dao.*;
import by.training.dao.impl.*;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao(Transaction transaction) {
        return new UserDaoImpl(transaction);
    }

    public SerialDao getSerialDao(Transaction transaction) {
        return new SerialDaoImpl(transaction);
    }

    public GenreDao getGenreDao(Transaction transaction) {
        return new GenreDaoImpl(transaction);
    }

    public CountryDaoImpl getCountryDao(Transaction transaction) {
        return new CountryDaoImpl(transaction);
    }

    public StudioDaoImpl getStudioDao(Transaction transaction) {
        return new StudioDaoImpl(transaction);
    }

    public CommentDao getCommentDao(Transaction transaction) {
        return new CommentDaoImpl(transaction);
    }
}
