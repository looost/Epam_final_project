package by.training.demoThreads.exercise20;

import java.util.concurrent.Exchanger;

class PutThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger ex) {

        this.exchanger = ex;
        message = "Hello Java!";
    }

    public void run() {

        try {
            message += " - It is the message from PutThread (" + Thread.currentThread().getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("PutThread " + Thread.currentThread().getName() + " получил: " + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
