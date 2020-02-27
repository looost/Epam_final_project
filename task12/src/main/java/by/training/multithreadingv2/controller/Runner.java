package by.training.multithreadingv2.controller;


import by.training.multithreadingv2.dao.exception.DaoException;
import by.training.multithreadingv2.dao.factory.DaoFactory;
import by.training.multithreadingv2.entity.Matrix;
import by.training.multithreadingv2.service.MatrixCountDownLatch;
import by.training.multithreadingv2.service.MatrixLocker;
import by.training.multithreadingv2.service.MatrixSemaphore;
import by.training.multithreadingv2.service.creator.MatrixCreator;
import by.training.multithreadingv2.service.parser.Parser;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) throws DaoException, InterruptedException {
        String str = DaoFactory.getInstance().getDao().readData();
        String[] st1 = Parser.parsFile(str, " ");
        Matrix matrix = MatrixCreator.createMatrix(st1);
        System.out.println(matrix);


        ReentrantLock lock = new ReentrantLock();
        MatrixLocker matrixLocker;

//        for (int i = 1; i < 6; i++) {
//            matrixLocker = new MatrixLocker(matrix, i, lock);
//            matrixLocker.start();
//        }

        Semaphore semaphore = new Semaphore(2);
        MatrixSemaphore matrixSemaphore;

//        for (int i = 1; i < 6; i++) {
//            matrixSemaphore = new MatrixSemaphore(matrix, i, semaphore);
//            matrixSemaphore.start();
//        }

        CountDownLatch countDownLatch = new CountDownLatch(3);
        MatrixCountDownLatch matrixCountDownLatch;

        for (int i = 1; i < 6; i++) {
            matrixCountDownLatch = new MatrixCountDownLatch(matrix, i, countDownLatch);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            matrixCountDownLatch.start();
        }

//        ThreadSaveCollection threadSaveCollection;
//
//        for (int i = 1; i < 6; i++) {
//            threadSaveCollection = new ThreadSaveCollection(matrix, i, lock);
//            threadSaveCollection.start();
//        }


        TimeUnit.MILLISECONDS.sleep(1000);

        System.out.println(matrix);
    }

}
