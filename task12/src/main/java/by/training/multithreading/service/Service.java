package by.training.multithreading.service;

import by.training.multithreading.dao.exception.DaoException;
import by.training.multithreading.dao.factory.DaoFactory;
import by.training.multithreading.service.exception.ServiceException;
import by.training.multithreading.service.parser.Parser;

public class Service {

    private static final String REGEX = " ";

    public String[] getMatrixValue(String path) throws ServiceException {
        try {
            String matrixValue = DaoFactory.getInstance().getDao().readData(path);
            return Parser.parsFile(matrixValue, REGEX);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String[] getThreadValue(String path) throws ServiceException {
        try {
            String threadValue = DaoFactory.getInstance().getDao().readData(path);
            return Parser.parsFile(threadValue, REGEX);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
