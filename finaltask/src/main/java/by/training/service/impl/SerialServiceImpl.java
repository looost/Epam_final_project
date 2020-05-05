package by.training.service.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.SerialService;
import by.training.service.exception.ServiceException;
import by.training.dao.Transaction;
import by.training.service.transaction.TransactionBuilder;
import by.training.service.transaction.TransactionBuilderFactory;
import by.training.service.transaction.TransactionUtil;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.SerialValidation;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SerialServiceImpl implements SerialService {

    private Validation<Serial> validation = new SerialValidation();
    private static final TransactionBuilder<Serial> SERIAL_TRANSACTION_HANDLER = TransactionBuilderFactory.SERIAL_TRANSACTION_BUILDER;

//    @Override
//    public Serial findSerialByName(String name) throws ServiceException {
//        try (Transaction transaction = new Transaction()) {
//            Serial serial = DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(name);
//            Serial result = TransactionUtil
//                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serial);
//            transaction.commit();
//            return result;
//        } catch (DaoException e) {
//            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public List<Serial> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findAll();
            serialList = TransactionUtil.select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialPageByPage(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialPageByPage(page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findMostLikedSerial(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findMostLikedSerial(page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> latestSerial(int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).latestSerial(limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllSerial() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerials();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialBySearchForm(SearchForm searchForm, int page, int limit) throws ServiceException {
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
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllSerialsBySearchForm(SearchForm searchForm) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsBySearchForm(searchForm);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialsThatIWatch(String userId, int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialsThatUserWatch(userId, page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllSerialsThatIWatch(String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsThatUserWatch(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialsThatILiked(String userId, int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialsThatUserLiked(userId, page, limit);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllSerialsThatILiked(String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getSerialDao(transaction).countAllSerialsThatUserLiked(userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean toWatchSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (!DaoFactory.getInstance().getSerialDao(transaction).userWatchThisSerial(serialId, userId)) {
                result = DaoFactory.getInstance().getSerialDao(transaction).toWatchSerial(userId, serialId);
                transaction.commit();
                return result;
            } else {
                throw new ServiceException(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean stopWatchSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getSerialDao(transaction).userWatchThisSerial(serialId, userId)) {
                boolean result = DaoFactory.getInstance().getSerialDao(transaction).stopWatchSerial(userId, serialId);
                transaction.commit();
                return result;
            } else {
                throw new ServiceException(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean userWatchThisSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                    .userWatchThisSerial(serialId, userId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean userLikedThisSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                    .userLikedThisSerial(userId, serialId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean likeSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (!DaoFactory.getInstance().getSerialDao(transaction).userLikedThisSerial(userId, serialId)) {
                boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                        .likeSerial(userId, serialId);
                transaction.commit();
                return result;
            } else {
                throw new ServiceException(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean dislikeSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (DaoFactory.getInstance().getSerialDao(transaction).userLikedThisSerial(userId, serialId)) {
                boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                        .dislikeSerial(userId, serialId);
                transaction.commit();
                return result;
            } else {
                throw new ServiceException(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Serial findById(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Serial serial = DaoFactory.getInstance().getSerialDao(transaction).findById(id);
            if (serial == null) {
                throw new ServiceException("Serial with id = " + id + " not found", HttpServletResponse.SC_NOT_FOUND);
            }
            serial = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serial);
            transaction.commit();
            return serial;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(Serial serial) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validation.isValid(transaction, serial)) {
                if (serial.getId() == 0) {
                    result = DaoFactory.getInstance().getSerialDao(transaction).create(serial);
                } else {
                    result = DaoFactory.getInstance().getSerialDao(transaction).update(serial);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Serial field not valid", HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
