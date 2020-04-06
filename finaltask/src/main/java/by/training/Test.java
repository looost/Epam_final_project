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

public class Test extends Super03 {
    public static void main(String... args) {
        Super03 obj = new Test();
        obj.method();
    }

    void method() {
        super.method();
        this.method("This ");
    }

    void method(String str) {
        System.out.println(str);
    }
}

class Super03 {
    void method() {
        System.out.print("Super ");
    }
}

