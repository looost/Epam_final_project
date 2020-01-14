package by.training.payment.servise;

import by.training.payment.dao.exception.DAOException;
import by.training.payment.dao.factory.DAOFactory;
import by.training.payment.entity.Payment;
import by.training.payment.servise.exeception.ServiceException;


public class Parser {
    public Payment parseFromFile() throws ServiceException {
        try {
            Payment payment = new Payment();
            String[] lines;
            for (String str : DAOFactory.getInstance().getDao().readData()
            ) {
                lines = str.split("; ");
                payment.addProduct(lines[0], Double.parseDouble(lines[1]));
            }
            return payment;
        } catch (DAOException e) {
            throw new ServiceException("Файл не найден!", e);
        }
    }
}
