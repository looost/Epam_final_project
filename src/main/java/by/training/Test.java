package by.training;


import by.training.model.AbstractEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Acc a1 = new Acc(200);
        Acc a2 = new Acc(500);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a1, a2, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a2, a1, 12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        System.out.println("success");
    }

    static void transfer(Acc a1, Acc a2, int amount) throws InterruptedException {
        synchronized (a1) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " взял и ждет");
            synchronized (a2) {
                a2.test(100);
                System.out.println("!!!!");
            }
        }
    }

}

class Acc {
    private int money;

    public Acc(int money) {
        this.money = money;
    }

    public void test(int amount) {
        money += amount;
    }
}