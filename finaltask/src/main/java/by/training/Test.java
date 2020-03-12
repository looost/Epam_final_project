package by.training;

import by.training.service.transaction.TransactionHandlerFactory;
import by.training.service.transaction.TransactionUtil;
import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

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
        Connection connection = ConnectionPool.getInstance().getConnection();
        Serial serial = DaoFactory.getInstance().getSerialDao(connection).findById("1");
        Serial serial1 = TransactionUtil.select(connection, TransactionHandlerFactory.SERIAL_TRANSACTION_HANDLER, serial);
        System.out.println(serial1);
    }
}
