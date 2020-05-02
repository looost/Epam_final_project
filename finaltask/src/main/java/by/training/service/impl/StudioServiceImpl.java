package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Studio;
import by.training.service.StudioService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.StudioValidation;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudioServiceImpl implements StudioService {

    private static final Validation<Studio> validator = new StudioValidation();

    @Override
    public List<Studio> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Studio> result = DaoFactory.getInstance().getStudioDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Studio> findStudioPageByPage(int page, int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Studio> result = DaoFactory.getInstance().getStudioDao(transaction).findStudioPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int countAllStudio() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getStudioDao(transaction).countAllStudio();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Studio findByName(String studioName) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Studio result = DaoFactory.getInstance().getStudioDao(transaction).findByName(studioName);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean delete(String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getStudioDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean save(Studio studio) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            if (validator.isValid(transaction, studio)) {
                if (studio.getId() == 0) {
                    result = DaoFactory.getInstance().getStudioDao(transaction).create(studio);
                } else {
                    result = DaoFactory.getInstance().getStudioDao(transaction).update(studio);
                }
                transaction.commit();
                return result;
            } else {
                transaction.rollback();
                throw new ServiceException("Not valid studio", HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
