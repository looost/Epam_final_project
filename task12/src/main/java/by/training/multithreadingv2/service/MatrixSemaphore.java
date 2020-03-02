package by.training.multithreadingv2.service;

import by.training.multithreadingv2.entity.Element;
import by.training.multithreadingv2.entity.Matrix;
import by.training.multithreadingv2.entity.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MatrixSemaphore extends Thread {

    private Logger logger = LogManager.getLogger("logger");
    private Semaphore semaphore;
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countChange = 0;

    public MatrixSemaphore(Matrix matrix, int value, Semaphore semaphore) {
        this.matrix = matrix;
        this.value = value;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (check()) {
            Element element = getOpenElement();
            if (element != null) {
                element.setValue(value);
                countChange++;
                logger.info(Thread.currentThread().getName() + " изменил значение на " + value);
                element.setStatus(Status.CHANGED);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Количество изменений у потока " + Thread.currentThread().getName() + " - " + countChange);
    }

    private Element getOpenElement() {
        boolean flag = false; // - для работы проверки семафора
        int index = ThreadLocalRandom.current().nextInt(arr.length);
        logger.info(Thread.currentThread().getName() + " хочет взять позицию " + index);
        try {
            semaphore.acquire();
            if (matrix.getElement(index, index).getValue() == 0 && matrix.getElement(index, index).getStatus().equals(Status.OPEN)) {
                logger.info(Thread.currentThread().getName() + " взял элемент " + index);
                matrix.getElement(index, index).setStatus(Status.CLOSE);
                flag = true;
                return matrix.getElement(index, index);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (flag) {                                       // Проверка работы семафора
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(Thread.currentThread().getName() + " УШЕЛ");
            }
            semaphore.release();
        }
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
