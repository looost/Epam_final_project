package by.training.demoThreads.exercise1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorldThread extends Thread {
    private static final Logger logger = LogManager.getLogger("logger");

    @Override
    public void run() {
        logger.info("Test Thread");
        logger.error("Test error Thread");
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        HelloWorldThread helloWorldThread = new HelloWorldThread();
        helloWorldThread.start();
    }
}
