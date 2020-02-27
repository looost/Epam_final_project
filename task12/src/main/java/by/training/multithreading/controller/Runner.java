package by.training.multithreading.controller;

import by.training.multithreading.dao.exception.DaoException;
import by.training.multithreading.dao.factory.DaoFactory;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.MatrixLocker;
import by.training.multithreading.service.MatrixSemaphore;
import by.training.multithreading.service.MatrixLockerTest;
import by.training.multithreading.service.creator.MatrixCreator;
import by.training.multithreading.service.exception.ServiceException;
import by.training.multithreading.service.parser.Parser;
import by.training.multithreadingv2.service.MatrixCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) throws DaoException, ServiceException, InterruptedException {
        String str = DaoFactory.getInstance().getDao().readData();
        String[] st1 = Parser.parsFile(str, " ");
        Matrix matrix = MatrixCreator.createMatrix(st1);
        ReentrantLock lock = new ReentrantLock();
        MatrixLocker thread;

//        for (int i = 1; i < 6; i++) {
//            thread = new MatrixLocker(matrix, i, lock);
//            thread.start();
//        }

        Semaphore semaphore = new Semaphore(4);
        MatrixSemaphore matrixSemaphore;


//        for (int i = 1; i < 6; i++) {
//            matrixSemaphore = new MatrixSemaphore(matrix, i, semaphore);
//            matrixSemaphore.start();
//        }



//        MatrixLockerTest threadMatrixLockerTest;
//        for (int i = 1; i < 6; i++) {
//            threadMatrixLockerTest = new MatrixLockerTest(matrix, i, lock);
//            threadMatrixLockerTest.start();
//        }


        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(matrix);
    }

}
