package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.*;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TransactionBuilderFactory {

    private TransactionBuilderFactory() {
    }

    public static final TransactionBuilder<Serial> SERIAL_TRANSACTION_BUILDER = new TransactionBuilder<Serial>() {
        @Override
        public Serial transaction(Transaction t, Serial entity) throws ServiceException {
            try {
                List<Genre> genreList = DaoFactory.getInstance().getGenreDao(t).findGenreBySerialId(String.valueOf(entity.getId()));
                entity.setGenres(genreList);

                Country country = DaoFactory.getInstance().getCountryDao(t).findById(String.valueOf(entity.getCountry().getId()));
                entity.getCountry().setName(country.getName());

                Studio studio = DaoFactory.getInstance().getStudioDao(t).findById(String.valueOf(entity.getStudio().getId()));
                entity.getStudio().setName(studio.getName());

                List<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForSerial(String.valueOf(entity.getId()));
                entity.setComments(commentSet);
                return entity;
            } catch (DaoException e) {
                throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    };

    public static final TransactionBuilder<Comment> COMMENT_TRANSACTION_BUILDER = new TransactionBuilder<Comment>() {
        @Override
        public Comment transaction(Transaction t, Comment entity) throws ServiceException {
            try {
                User user = DaoFactory.getInstance().getUserDao(t).findById(String.valueOf(entity.getUser().getId()));
                entity.setUser(user);
                Serial serial = DaoFactory.getInstance().getSerialDao(t).findById(String.valueOf(entity.getSerial().getId()));
                entity.setSerial(serial);
                return entity;
            } catch (DaoException e) {
                throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    };

    public static <T> TransactionBuilder<T> getSingleTransactionBuilder(final TransactionBuilder<T> builder) {
        return new TransactionBuilder<T>() {
            @Override
            public T transaction(Transaction t, T entity) throws ServiceException {
                return builder.transaction(t, entity);
            }
        };
    }

    public static <T> TransactionBuilder<List<T>> getListTransactionBuilder(final TransactionBuilder<T> builder) {
        return new TransactionBuilder<List<T>>() {
            @Override
            public List<T> transaction(Transaction t, List<T> entity) throws ServiceException {
                for (T e : entity
                ) {
                    builder.transaction(t, e);
                }
                return entity;
            }
        };
    }
}