package by.training;

import by.training.dao.exception.DaoException;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

public class Test {
    public static void main(String[] args) throws DaoException, ServiceException {
//        Set<Comment> commentSet = ServiceFactory.getInstance().getCommentService().findAllCommentForSerial("1");
//        commentSet.forEach(comment -> System.out.println(comment));

//        System.out.println(ServiceFactory.getInstance().getCommentService().findById("13"));

        Serial serial = ServiceFactory.getInstance().getSerialService().findById("3");
        //Serial serial = ServiceFactory.getInstance().getSerialService().findSerialByName("WestWorld");
        System.out.println(serial);


    }
}
