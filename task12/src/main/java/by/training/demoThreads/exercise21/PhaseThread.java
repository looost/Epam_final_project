package by.training.demoThreads.exercise21;

import java.util.concurrent.Phaser;

class PhaseThread implements Runnable {

    Phaser phaser;
    String name;

    PhaseThread(Phaser p, String n) {

        this.phaser = p;
        this.name = n;
        phaser.register();
    }

    public void run() {

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты
    }
}
