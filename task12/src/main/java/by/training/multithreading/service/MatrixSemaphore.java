package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MatrixSemaphore extends Thread {
    private Semaphore semaphore;
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public MatrixSemaphore(Matrix matrix, int value, Semaphore semaphore) {
        this.matrix = matrix;
        this.value = value;
        this.semaphore = semaphore;
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
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " хочет записать в ячейку " + "(" + bound + ", " + bound + ")" + " значение - " + value);
                if (bound != -1 && matrix.getElement(bound, bound) == 0) {
                    matrix.setElement(bound, bound, value);
                    arr[rand] = -1;
                    System.out.println(Thread.currentThread().getName() + " записал в ячейку " + "(" + bound + ", " + bound + ") "
                            + " значение - " + value);
                }
            } catch (InterruptedException e) {
                // logger
            } finally {
                semaphore.release();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
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
