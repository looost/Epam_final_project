package by.training.main;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            List<Serial> serialList = DaoFactory.getInstance().getSerialDao(ConnectionPool.getInstance().getConnection())
                    .findSerialBySearchForm("Мир");
            System.out.println(serialList.size());
            serialList.forEach(System.out::println);

//            List <Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
//            genres.forEach(System.out::println);

        }
//        catch (  ServiceException e) {
//            e.printStackTrace();
//        }
        catch (DaoException e) {
        }
    }
}
