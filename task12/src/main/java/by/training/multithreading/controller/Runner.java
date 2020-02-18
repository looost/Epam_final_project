package by.training.multithreading.controller;

import by.training.multithreading.dao.exception.DaoException;
import by.training.multithreading.dao.factory.DaoFactory;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.creator.MatrixCreator;
import by.training.multithreading.service.exception.ServiceException;
import by.training.multithreading.service.parser.Parser;

public class Runner {
    public static void main(String[] args) throws DaoException, ServiceException {
        String str = DaoFactory.getInstance().getDao().readData();
        System.out.println(str);
        String[] st1 = Parser.parsFile(str, " ");
        Matrix matrix = MatrixCreator.createMatrix(st1);
        System.out.println(matrix);
    }

}
