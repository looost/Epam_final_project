package by.training.main;

import by.training.dao.exception.DaoException;
import by.training.dao.serial.SerialDao;
import by.training.dao.serial.impl.SerialDaoImpl;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, DaoException, ServiceException {
        SerialDao serialDao = new SerialDaoImpl();
        Serial entity = ServiceFactory.getInstance().getSerialService().findById("5");
        System.out.println(entity);
    }
}
