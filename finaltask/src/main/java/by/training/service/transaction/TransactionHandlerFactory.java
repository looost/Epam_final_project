package by.training.service.transaction;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.*;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TransactionHandlerFactory {

    private TransactionHandlerFactory() {
    }

    public static final TransactionHandler<Serial> SERIAL_TRANSACTION_HANDLER = new TransactionHandler<Serial>() {
        @Override
        public Serial transaction(Connection c, Serial entity) throws ServiceException {
            try {
                List<Genre> genreList = DaoFactory.getInstance().getGenreDao(c).findGenreBySerialId(String.valueOf(entity.getId()));
                entity.setGenres(genreList);

                Country country = DaoFactory.getInstance().getCountryDao(c).findById(String.valueOf(entity.getCountry().getId()));
                entity.getCountry().setName(country.getName());

                Studio studio = DaoFactory.getInstance().getStudioDao(c).findById(String.valueOf(entity.getStudio().getId()));
                entity.getStudio().setName(studio.getName());

                List<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForSerial(String.valueOf(entity.getId()));
                entity.setComments(commentSet);
                return entity;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    };

    public static final TransactionHandler<Comment> COMMENT_TRANSACTION_HANDLER = new TransactionHandler<Comment>() {
        @Override
        public Comment transaction(Connection c, Comment entity) throws ServiceException {
            try {
                User user = DaoFactory.getInstance().getUserDao(c).findById(String.valueOf(entity.getUser().getId()));
                entity.setUser(user);
                Serial serial = DaoFactory.getInstance().getSerialDao(c).findById(String.valueOf(entity.getSerial().getId()));
                entity.setSerial(serial);
                return entity;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    };

    public static <T> TransactionHandler<T> getSingleTransactionHandler(final TransactionHandler<T> handler) {
        return new TransactionHandler<T>() {
            @Override
            public T transaction(Connection c, T entity) throws ServiceException {
                try {
                    c.setAutoCommit(false);
                    T result = handler.transaction(c, entity);
                    c.commit();
                    ConnectionPool.getInstance().close(c); //TODO close and finally problem (exception in finally block)
                    return result;
                } catch (DaoException | SQLException e) {
                    throw new ServiceException(e);
                }
            }
        };
    }

    public static <T> TransactionHandler<List<T>> getListTransactionHandler(final TransactionHandler<T> handler) {
        return new TransactionHandler<List<T>>() {
            @Override
            public List<T> transaction(Connection c, List<T> entity) throws ServiceException {
                try {
                    c.setAutoCommit(false);
                    for (T e : entity
                    ) {
                        handler.transaction(c, e);
                    }
                    c.commit();
                    ConnectionPool.getInstance().close(c);
                    return entity;
                } catch (DaoException | SQLException e) {
                    throw new ServiceException(e);
                }
            }
        };
    }
}
