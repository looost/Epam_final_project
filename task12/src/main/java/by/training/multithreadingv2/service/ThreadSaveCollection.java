package by.training.multithreadingv2.service;

import by.training.multithreadingv2.entity.Element;
import by.training.multithreadingv2.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSaveCollection extends Thread {

    private Logger logger = LogManager.getLogger("logger");
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countChange = 0;
    private CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    private ReentrantLock lock;

    public ThreadSaveCollection(Matrix matrix, int value, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.lock = lock;

        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            list.add(matrix.getElement(i, i));
        }

    }

    @Override
    public void run() {
        while (check()) {
            int index = ThreadLocalRandom.current().nextInt(arr.length);

            lock.lock();
            if (((Element) list.get(index)).getValue() == 0) {
                logger.info(Thread.currentThread().getName() + " записывает в ячейку - " + index + " значение - " + value);
                ((Element) list.get(index)).setValue(value);
                countChange++;
            }
            lock.unlock();
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Количество изменений у потока " + Thread.currentThread().getName() + " - " + countChange);
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
