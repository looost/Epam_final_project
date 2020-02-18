package by.training.demoThreads.exercise3;

public class PriorThread extends Thread {
    public PriorThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}

