package by.training.multithreading.service;

import by.training.multithreading.entity.Element;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.entity.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixPhaser extends Thread {

    private Logger logger = LogManager.getLogger("logger");
    private Phaser phaser;
    private ReentrantLock lock;
    private Matrix matrix;
    private int value;
    private Random random = new Random();
    private int countChange = 0;

    public MatrixPhaser(Matrix matrix, int value, Phaser phaser, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.phaser = phaser;
        this.lock = lock;
    }

    @Override
    public void run() {

        while (check()) {
            boolean take = false;
            Element element = getOpenElement();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                logger.info(Thread.currentThread().getName() + " ждет");
                phaser.arriveAndAwaitAdvance();
                if (element != null) {
                    take = true;
                    element.setValue(value);
                    countChange++;
                    logger.info(Thread.currentThread().getName() + " изменил значение на " + value);
                    element.setStatus(Status.CHANGED);
                }
            } finally {
                phaser.arriveAndDeregister();
                try {
                    if (take) {
                        TimeUnit.MILLISECONDS.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("Количество изменений у потока " + Thread.currentThread().getName() + " - " + countChange);
    }

    private Element getOpenElement() {
        int index = random.nextInt(matrix.getHorizontalSize());
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
}