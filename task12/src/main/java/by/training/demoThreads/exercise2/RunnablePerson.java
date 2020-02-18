package by.training.demoThreads.exercise2;


import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable {

    public RunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("-----------" + Thread.currentThread().getName() + "---" + Thread.currentThread().getPriority());
            System.out.println(i + ")" + getName() + ": Hello World!");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("EXXX");
            }
        }
    }

    public static void main(String[] args) {
        RunnablePerson p1 = new RunnablePerson("Alica");
        Thread t1 = new Thread(p1, "Alica Tread");
        t1.setPriority(5);
        t1.start();
        RunnablePerson p2 = new RunnablePerson("Bob");
        Thread t2 = new Thread(p2, "Bob Thread");
        t2.start();

//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.err.println("END");
    }
}
