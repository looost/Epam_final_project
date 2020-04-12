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
    public static void main(String... args) {
        String str = "/sdgs/index.html";
        String str2 = str.substring(str.indexOf("/") + 1, str.lastIndexOf('.')).replaceAll("/", "-");
        System.out.println(str2);
    }
}

