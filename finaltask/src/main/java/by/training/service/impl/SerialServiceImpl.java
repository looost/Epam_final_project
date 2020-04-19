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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SerialServiceImpl implements SerialService {

    private static final TransactionBuilder<Serial> SERIAL_TRANSACTION_HANDLER = TransactionBuilderFactory.SERIAL_TRANSACTION_BUILDER;

    @Override
    public Serial findSerialByName(String name) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Serial serial = DaoFactory.getInstance().getSerialDao(transaction).findSerialByName(name);
            Serial result = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getSingleTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serial);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

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
    public List<Serial> findAllSerial2(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findAllSerial2(page, limit);
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
    public List<Serial> findSerialBySearchForm(SearchForm searchForm) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            if (searchForm.getQuery() == null) {
                searchForm.setQuery("");
            }
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialBySearchForm(searchForm);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialByGenre(String genreId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialByGenre(genreId);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Serial> findSerialsThatIWatch(String userId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(transaction).findSerialsThatIWatch(userId);
            serialList = TransactionUtil
                    .select(transaction, TransactionBuilderFactory.getListTransactionBuilder(SERIAL_TRANSACTION_HANDLER), serialList);
            transaction.commit();
            return serialList;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean toWatchSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction).toWatchSerial(userId, serialId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean stopWatchSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction).stopWatchSerial(userId, serialId);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean userWatchThisSerial(String userId, String serialId) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction)
                    .userWatchThisSerial(serialId, userId) != null;
            transaction.commit();
            return result;
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
    public boolean create(Serial entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int index = DaoFactory.getInstance().getSerialDao(transaction).createAndReturnIndex(entity);
            DaoFactory.getInstance().getSerialDao(transaction).createSerialGenre(index, entity.getGenres());
            transaction.commit();
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean update(Serial entity) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getSerialDao(transaction).update(entity);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
