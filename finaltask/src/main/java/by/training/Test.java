package by.training;

import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.mindrot.jbcrypt.BCrypt;

public class Test extends Thread {
    public static void main(String[] args) throws ServiceException {
        System.out.println(ServiceFactory.getInstance().getUserService().findByLogin("admin"));
    }
}

