package by.training.dao.factory;


import by.training.dao.CommentDao;
import by.training.dao.GenreDao;
import by.training.dao.SerialDao;
import by.training.dao.UserDao;
import by.training.dao.Transaction;
import by.training.dao.impl.*;

/**
 * Class for accessing a specific DAO. Represents an implementation of the Singleton design pattern.
 *
 * @see Transaction
 */
public final class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private DaoFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }


    /**
     * Gets user dao.
     *
     * @param transaction the transaction
     * @return the user dao
     */
    public UserDao getUserDao(final Transaction transaction) {
        return new UserDaoImpl(transaction);
    }

    /**
     * Gets serial dao.
     *
     * @param transaction the transaction
     * @return the serial dao
     */
    public SerialDao getSerialDao(final Transaction transaction) {
        return new SerialDaoImpl(transaction);
    }

    /**
     * Gets genre dao.
     *
     * @param transaction the transaction
     * @return the genre dao
     */
    public GenreDao getGenreDao(final Transaction transaction) {
        return new GenreDaoImpl(transaction);
    }

    /**
     * Gets country dao.
     *
     * @param transaction the transaction
     * @return the country dao
     */
    public CountryDaoImpl getCountryDao(final Transaction transaction) {
        return new CountryDaoImpl(transaction);
    }

    /**
     * Gets studio dao.
     *
     * @param transaction the transaction
     * @return the studio dao
     */
    public StudioDaoImpl getStudioDao(final Transaction transaction) {
        return new StudioDaoImpl(transaction);
    }

    /**
     * Gets comment dao.
     *
     * @param transaction the transaction
     * @return the comment dao
     */
    public CommentDao getCommentDao(final Transaction transaction) {
        return new CommentDaoImpl(transaction);
    }
}
