package by.training.demoThreads.exercise6;

public class ThreadsApp {

    public static void main(String[] args) {

        CommonResource commonResource = new CommonResource();
        CommonResource1 commonResource1 = new CommonResource1();
        for (int i = 1; i < 5; i++) {

            Thread t = new Thread(new CountThread(commonResource1));
            t.setName("Поток " + i);
            t.start();
        }
    }
}
