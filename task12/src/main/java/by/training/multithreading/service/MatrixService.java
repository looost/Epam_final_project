package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService extends Thread {
    private ReentrantLock locker;
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public MatrixService(Matrix matrix, int value, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.locker = lock;
    }

    @Override
    public void run() {
        Random random = new Random();
        int bound;
        int rand;
        boolean flag = true;
        while (flag) {
            rand = random.nextInt(10);
            bound = arr[rand];
            try {
                locker.lock();
                if (bound != -1 && matrix.getElement(bound, bound) == 0) {
                    matrix.setElement(bound, bound, value);
                    arr[rand] = -1;
                    System.out.println(Thread.currentThread().getName() + " записал в ячейку " + "(" + bound + ", " + bound + ") "
                            + " значение - " + value);
                }
            } finally {
                locker.unlock();
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = check();
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    private boolean check() {
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                if (i == j && matrix.getElement(i, j) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
