package by.training.service.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Studio;
import by.training.service.StudioService;
import by.training.service.exception.ServiceException;
import by.training.service.validation.Validation;
import by.training.service.validation.impl.StudioValidationImpl;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link StudioService} interface. Provides access to {@link by.training.dao.StudioDao}
 * and provides support for working with the entity {@link Studio}.
 *
 * @see Transaction
 * @see DaoException
 */
public class StudioServiceImpl implements StudioService {

    /**
     * Validator for this Service.
     */
    private static final Validation<Studio> VALIDATOR = new StudioValidationImpl();

    /**
     * Find all studios.
     *
     * @return the {@link List} of ${@link Studio} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Studio> findAll() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Studio> result = DaoFactory.getInstance().getStudioDao(transaction).findAll();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find studio page by page.
     *
     * @param page  the page
     * @param limit the limit
     * @return the {@link List} of ${@link Studio} and empty {@link List} if no results are found
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public List<Studio> findStudioPageByPage(final int page, final int limit) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            List<Studio> result = DaoFactory.getInstance().getStudioDao(transaction).findStudioPageByPage(page, limit);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Count of all studio.
     *
     * @return number of all studios
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public int countAllStudio() throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            int result = DaoFactory.getInstance().getStudioDao(transaction).countAllStudio();
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Find studio by name.
     *
     * @param studioName the studio name
     * @return the studio and null if does not exist
     * @throws ServiceException if there is an error on the DAO layer
     */
    @Override
    public Studio findByName(final String studioName) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            Studio result = DaoFactory.getInstance().getStudioDao(transaction).findByName(studioName);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Delete studio.
     *
     * @param id the id
     * @return true if studio was made deleted and false otherwise.
     * @throws ServiceException if the method failed
     */
    @Override
    public boolean delete(final String id) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result = DaoFactory.getInstance().getStudioDao(transaction).delete(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }

    /**
     * Save studio.
     *
     * @param studio the studio
     * @return true if studio was made saved and false otherwise.
     * @throws ServiceException if there is an error on the DAO layer or have validation problem
     */
    @Override
    public boolean save(final Studio studio) throws ServiceException {
        try (Transaction transaction = new Transaction()) {
            boolean result;
            Map<String, String> errors = VALIDATOR.isValid(studio);
            if (errors.isEmpty()) {
                if (studio.getId() == 0) {
                    result = DaoFactory.getInstance().getStudioDao(transaction).create(studio);
                } else {
                    result = DaoFactory.getInstance().getStudioDao(transaction).update(studio);
                }
                transaction.commit();
                return result;
            } else {
                throw new ServiceException("Not valid studio", ServiceException.BAD_REQUEST, errors);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceException.DAO_LAYER_ERROR);
        }
    }
}
