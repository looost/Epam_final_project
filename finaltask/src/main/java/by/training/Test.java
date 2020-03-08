package by.training;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.dao.jdbc.JDBCUtil;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws DaoException, ServiceException, SQLException {
//        Set<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForSerial("1");
//        commentSet.forEach(comment -> System.out.println(comment));

//        System.out.println(ServiceFactory.getInstance().getCommentService().findById("13"));

//        Serial serial = ServiceFactory.getInstance().getSerialService().findById("3");
//        Serial serial = ServiceFactory.getInstance().getSerialService().findSerialByName("WestWorld");
//        System.out.println(serial);
        //List <Serial> serialList = DaoFactory.getInstance().getSerialDao(ConnectionPool.getInstance().getConnection()).findAll();
//        List <Serial> serialList =
//        serialList.forEach(serial -> System.out.println(serial));

        Serial serial = ServiceFactory.getInstance().getSerialService().findById("1");
        serial.setName("Мир Дикого Запада");
        boolean f = DaoFactory.getInstance().getSerialDao(ConnectionPool.getInstance().getConnection()).update(serial);
        System.out.println(f);
    }
}
