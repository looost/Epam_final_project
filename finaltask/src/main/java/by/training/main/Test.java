package by.training.main;

import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.dao.genre.impl.GenreDaoImpl;
import by.training.dao.serial.SerialDao;
import by.training.dao.serial.impl.SerialDaoImpl;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, DaoException, ServiceException {
        Serial entity = DaoFactory.getInstance().getSerialDao().findById("1");
        System.out.println(entity);
        Serial serial = ServiceFactory.getInstance().getSerialService().findById("1");
        System.out.println("0------");
        System.out.println(serial);
//       GenreDaoImpl genreDao = new GenreDaoImpl();
//        System.out.println(genreDao.findByName("Drama"));
//        System.out.println(genreDao.getProperties().getProperty("genreName"));
    }
}
