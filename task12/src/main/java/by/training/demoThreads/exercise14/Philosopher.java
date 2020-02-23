package by.training.demoThreads.exercise14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Philosopher extends Thread {
    Semaphore sem; // семафор. ограничивающий число философов
    // кол-во приемов пищи
    int num = 0;
    // условный номер философа
    int id;

    // в качестве параметров конструктора передаем идентификатор философа и семафор
    Philosopher(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
    }

    public void run() {
        try {
            while (num < 3)// пока количество приемов пищи не достигнет 3
            {
                // Запрашиваем у семафора разрешение на выполнение
                sem.acquire();
                System.out.println("Философ " + id + " садится за стол");
                // философ ест
                TimeUnit.MILLISECONDS.sleep(500);
                num++;

                System.out.println("Философ " + id + " выходит из-за стола");
                sem.release();

                // философ гуляет
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("у философа " + id + " проблемы со здоровьем");
        }
    }
}

