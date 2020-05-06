package by.training.service.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.*;
import by.training.model.form.SearchForm;
import by.training.service.SerialService;
import by.training.service.exception.ServiceException;
import by.training.dao.Transaction;
import by.training.service.factory.ServiceFactory;
import by.training.service.transaction.TransactionBuilder;
import by.training.service.transaction.TransactionBuilderFactory;
import by.training.service.transaction.TransactionUtil;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.SerialValidation;

import java.util.List;

/**
 * Implementation of {@link Serial} interface. Provides access to {@link by.training.dao.SerialDao}
 * and provides support for working with the entity {@link Serial}.
 *
 * @see Transaction
 * @see DaoException
 */
public class SerialServiceImpl implements SerialService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Serial> VALIDATOR = new SerialValidation();

    /**
     * Implementation of {@link TransactionBuilder} functional interface.
     * Needs for build {@link Serial} from different DAO classes.
     */
    private static final TransactionBuilder<Serial> SERIAL_TRANSACTION_HANDLER = new TransactionBuilder<Serial>() {
        @Override
        public Serial transaction(final Transaction t, final Serial entity) throws ServiceException {
            try {
                if (entity == null) {
                    throw new ServiceException("Serial does not exist", ServiceException.NOT_FOUND);
                }
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
                throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
            }
        }
    };

    /**
     * Find all serial.
     *
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findAll();
            serialList = TransactionUtil.select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find serial page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> findSerialPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialPageByPage(page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find most liked serial.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> findMostLikedSerial(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findMostLikedSerial(page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Latest serial.
     *
     * @param limit the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> latestSerial(final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).latestSerial(limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all serials.
     *
     * @return number of all serials
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllSerial() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerials();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find serial by search form.
     *
     * @param searchForm the search form
     * @param page       the page
     * @param limit      the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     * @see SearchForm
     */
    @Override
    public List<Serial> findSerialBySearchForm(final SearchForm searchForm,
                                               final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (searchForm.getQuery() == null) {
                searchForm.setQuery("");
            }
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialBySearchForm(searchForm, page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all serials by search form.
     *
     * @param searchForm the search form
     * @return number of all serials by search form
     * @throws ServiceException if there is an error on the DAO layer
     * @see SearchForm
     */
    @Override
    public int countAllSerialsBySearchForm(final SearchForm searchForm) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsBySearchForm(searchForm);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find serials that user watch.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> findSerialsThatUserWatch(final String userId,
                                                 final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialsThatUserWatch(userId, page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all serials that user watch.
     *
     * @param userId the user id
     * @return number of serials that is watch user
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllSerialsThatUserWatch(final String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsThatUserWatch(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find serials that user liked.
     *
     * @param userId the user id
     * @param page   the page
     * @param limit  the limit
     * @return the {@link List} of ${@link Serial} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Serial> findSerialsThatUserLiked(final String userId,
                                                 final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialsThatUserLiked(userId, page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all serials that user liked.
     *
     * @param userId the user id
     * @return number of serials that liked user
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllSerialsThatUserLiked(final String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsThatUserLiked(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * To watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if there is an error on the DAO layer or if the user is already watching this serial
     */
    @Override
    public void toWatchSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (!DaoFactory.getInstance().getSerialDao(transaction).userWatchThisSerial(serialId, userId)) {
                DaoFactory.getInstance().getSerialDao(transaction).toWatchSerial(userId, serialId);
                transaction.commit();
            } else {
                throw new ServiceException("Serial already watching", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Stop watch serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if there is an error on the DAO layer or if the user does not watch the serial
     */
    @Override
    public void stopWatchSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getSerialDao(transaction).userWatchThisSerial(serialId, userId)) {
                DaoFactory.getInstance().getSerialDao(transaction).stopWatchSerial(userId, serialId);
                transaction.commit();
            } else {
                throw new ServiceException("Serial does not watch", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Check if the user is watching this serial.
     *
     * @param serialId the serial id
     * @param userId   the user id
     * @return true if user watch this serial and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean userWatchThisSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                    .userWatchThisSerial(serialId, userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Check whether the user likes this serial.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @return true if user liked this serial and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean userLikedThisSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                    .userLikedThisSerial(userId, serialId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Add a serial to user liked.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if there is an error on the DAO layer or if the user is already liked this serial
     */
    @Override
    public void likeSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (!DaoFactory.getInstance().getSerialDao(transaction).userLikedThisSerial(userId, serialId)) {
                DaoFactory.getInstance().getSerialDao(transaction)
                        .likeSerial(userId, serialId);
                transaction.commit();
            } else {
                throw new ServiceException("Serial already liked", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Remove a serial to user liked.
     *
     * @param userId   the user id
     * @param serialId the serial id
     * @throws ServiceException if there is an error on the DAO layer or if the user does not like the serial
     */
    @Override
    public void dislikeSerial(final String userId, final String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getSerialDao(transaction).userLikedThisSerial(userId, serialId)) {
                DaoFactory.getInstance().getSerialDao(transaction)
                        .dislikeSerial(userId, serialId);
                transaction.commit();
            } else {
                throw new ServiceException("Serial does not like", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find serial by id.
     *
     * @param id the serial id
     * @return the serial
     * @throws ServiceException if there is an error on the DAO layer or serial does not exist
     */
    @Override
    public Serial findById(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Serial serial = DaoFactory.getInstance().getSerialDao(transaction).findById(id);
            serial = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serial);
            transaction.commit();
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete serial.
     *
     * @param id the id
     * @return true if serial was made deleted and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save serial.
     *
     * @param serial the serial
     * @return true if serial was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Serial serial) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (VALIDATOR.isValid(transaction, serial)) {
                if (serial.getId() == 0) {
                    result = DaoFactory.getInstance().getSerialDao(transaction).create(serial);
                } else {
                    result = DaoFactory.getInstance().getSerialDao(transaction).update(serial);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Serial field not valid", ServiceException.BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
