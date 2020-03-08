package by.training.multithreading.service;

import by.training.multithreading.entity.Element;
import by.training.multithreading.entity.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSaveCollection extends Thread {

    private Logger logger = LogManager.getLogger("logger");
    private Matrix matrix;
    private int value;
    private Random random = new Random();
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
            int index = random.nextInt(matrix.getHorizontalSize());
 // TODO использовать remove
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
