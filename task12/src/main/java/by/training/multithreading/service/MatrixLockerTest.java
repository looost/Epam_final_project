package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixLockerTest extends Thread {
    private ReentrantLock locker;
    private Matrix matrix;
    private int value;
    private static List<Integer> list;

    static {
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
    }

    public MatrixLockerTest(Matrix matrix, int value, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.locker = lock;
    }

    @Override
    public void run() {
        int bound;
        boolean flag = true;
        int index;
        while (flag) {
            bound = getRandomList(list);
            index = list.indexOf(bound);
            try {
                locker.lock();
                if (bound != -1 && matrix.getElement(bound, bound) == 0) {
                    matrix.setElement(bound, bound, value);
                    list.set(index, -1);
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

    private int getRandomList(List<Integer> list) {
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }

    private boolean check() {

        int length = this.matrix.getVerticalSize();
        for (int i = 0; i < length; i++) {
            if (matrix.getElement(i,i) != 0) {
                return false;
            }
        }
        return true;
    }
}
