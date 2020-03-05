package by.training.multithreading.controller;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.*;
import by.training.multithreading.service.creator.MatrixCreator;
import by.training.multithreading.service.exception.ServiceException;
import by.training.multithreading.service.factory.ServiceFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) throws InterruptedException, ServiceException {

        String[] matrixValue = ServiceFactory.getInstance().getService().getMatrixValue("src\\main\\java\\by\\training\\multithreading\\data\\matrix.txt");
        String[] threadValue = ServiceFactory.getInstance().getService().getThreadValue("src\\main\\java\\by\\training\\multithreading\\data\\thread.txt");
        int matrixSize = 11;

        Matrix matrix = MatrixCreator.createMatrix(matrixValue, matrixSize);

        System.out.println(matrix);

        ReentrantLock lock = new ReentrantLock();
        MatrixLocker matrixLocker;

//        for (int i = 0; i < matrixSize/2; i++) {
//            matrixLocker = new MatrixLocker(matrix, Integer.parseInt(threadValue[i]), lock);
//            matrixLocker.start();
//        }

        Semaphore semaphore = new Semaphore(2);
        MatrixSemaphore matrixSemaphore;

//        for (int i = 0; i < matrixSize/2; i++) {
//            matrixSemaphore = new MatrixSemaphore(matrix, Integer.parseInt(threadValue[i]), semaphore);
//            matrixSemaphore.start();
//        }

        CountDownLatch countDownLatch = new CountDownLatch(3);
        MatrixCountDownLatch matrixCountDownLatch;

//        for (int i = 0; i < matrixSize/2; i++) {
//            matrixCountDownLatch = new MatrixCountDownLatch(matrix, Integer.parseInt(threadValue[i]), countDownLatch, lock);
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            matrixCountDownLatch.start();
//        }

        ThreadSaveCollection threadSaveCollection;

//        for (int i = 0; i < matrixSize/2; i++) {
//            threadSaveCollection = new ThreadSaveCollection(matrix, Integer.parseInt(threadValue[i]), lock);
//            threadSaveCollection.start();
//        }

        Phaser phaser = new Phaser(4);
        MatrixPhaser matrixPhaser;

        for (int i = 0; i < matrixSize / 2; i++) {
            matrixPhaser = new MatrixPhaser(matrix, Integer.parseInt(threadValue[i]), phaser, lock);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            matrixPhaser.start();
        }


        TimeUnit.MILLISECONDS.sleep(4000);

        System.out.println(matrix);
    }

}
