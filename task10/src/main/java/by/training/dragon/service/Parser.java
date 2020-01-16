package by.training.dragon.service;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.dao.factory.DAOFactory;
import by.training.dragon.service.exception.ServiceException;

public class Parser {

    public String[] parsFile(int i) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDao().readData().get(i).split("; ");
        } catch (DAOException e) {
            throw new ServiceException("Файл не найден!");
        }
    }
}
