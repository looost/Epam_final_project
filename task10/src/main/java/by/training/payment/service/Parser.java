package by.training.payment.service;

import by.training.payment.dao.exception.DAOException;
import by.training.payment.dao.factory.DAOFactory;
import by.training.payment.service.exeception.ServiceException;


public class Parser {

    public String[] parsFile(int i) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDao().readData().get(i).split("; ");
        } catch (DAOException e) {
            throw new ServiceException("Файл не найден!");
        }
    }

}
