package by.training.multithreading.controller;

import by.training.multithreading.dao.exception.DaoException;
import by.training.multithreading.dao.factory.DaoFactory;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.MatrixService;
import by.training.multithreading.service.creator.MatrixCreator;
import by.training.multithreading.service.exception.ServiceException;
import by.training.multithreading.service.parser.Parser;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) throws DaoException, ServiceException, InterruptedException {
        String str = DaoFactory.getInstance().getDao().readData();
        String[] st1 = Parser.parsFile(str, " ");
        Matrix matrix = MatrixCreator.createMatrix(st1);
        ReentrantLock lock = new ReentrantLock();
        MatrixService thread;


        for (int i = 1; i < 6; i++) {
            thread = new MatrixService(matrix, i, lock);
            thread.start();
        }

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(matrix);
    }

}
