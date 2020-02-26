package by.training.multithreadingv2.service;


import by.training.multithreadingv2.entity.Element;
import by.training.multithreadingv2.entity.Matrix;
import by.training.multithreadingv2.entity.Status;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixLocker extends Thread {
    private ReentrantLock lock;
    private Matrix matrix;
    private int value;
    private int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int countChange = 0;

    public MatrixLocker(Matrix matrix, int value, ReentrantLock lock) {
        this.matrix = matrix;
        this.value = value;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (check()) {
            Element element = getOpenElement();
            if (element != null) {
                element.setValue(value);
                countChange++;
                System.out.println(Thread.currentThread().getName() + " изменил значение на " + value);
                element.setStatus(Status.CHANGED);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Количество изменений у потока " + Thread.currentThread().getName() + " - " + countChange);
    }

    private Element getOpenElement() {
        int index = ThreadLocalRandom.current().nextInt(arr.length);
        System.out.println(Thread.currentThread().getName() + " хочет взять позицию " + index);
        lock.lock();
        if (matrix.getElement(index, index).getValue() == 0 && matrix.getElement(index, index).getStatus().equals(Status.OPEN)) {
            System.out.println(Thread.currentThread().getName() + " взял элемент " + index);
            matrix.getElement(index, index).setStatus(Status.CLOSE);
            lock.unlock();
            return matrix.getElement(index, index);
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " не смог взять позицию " + index);
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
