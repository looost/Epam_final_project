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
                    .findSerialsThatIWatch("3");
            serialList.forEach(System.out::println);
        }
        catch (DaoException e) {
        }
    }
}
