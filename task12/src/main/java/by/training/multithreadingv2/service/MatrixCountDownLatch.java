package by.training.multithreadingv2.service;

import by.training.multithreadingv2.entity.Element;
import by.training.multithreadingv2.entity.Matrix;
import by.training.multithreadingv2.entity.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixCountDownLatch extends Thread {

    private Logger logger = LogManager.getLogger("logger");
    private CountDownLatch countDownLatch;
    private ReentrantLock lock;
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countChange = 0;

    public MatrixCountDownLatch(Matrix matrix, int value, CountDownLatch countDownLatch, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.countDownLatch = countDownLatch;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (check()) {

                Element element = getOpenElement();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();


            try {
                logger.info(Thread.currentThread().getName() + " ждет");
                countDownLatch.await();
                if (element != null) {
                    element.setValue(value);
                    countChange++;
                    logger.info(Thread.currentThread().getName() + " изменил значение на " + value);
                    element.setStatus(Status.CHANGED);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("Количество изменений у потока " + Thread.currentThread().getName() + " - " + countChange);
    }

    private Element getOpenElement() {
        int index = ThreadLocalRandom.current().nextInt(arr.length);
        logger.info(Thread.currentThread().getName() + " хочет взять позицию " + index);
        lock.lock();
        if (matrix.getElement(index, index).getValue() == 0 && matrix.getElement(index, index).getStatus().equals(Status.OPEN)) {
            logger.info(Thread.currentThread().getName() + " взял элемент " + index);
            matrix.getElement(index, index).setStatus(Status.CLOSE);
            lock.unlock();
            return matrix.getElement(index, index);
        }
        lock.unlock();
        logger.info(Thread.currentThread().getName() + " не смог взять позицию " + index);
        return null;
    }

    private boolean check() {
        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            if (matrix.getElement(i, i).getValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
