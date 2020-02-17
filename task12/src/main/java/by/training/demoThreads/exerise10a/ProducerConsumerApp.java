package by.training.demoThreads.exerise10a;

public class ProducerConsumerApp {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store, "Maria").start();
        new Producer(store, "Fedya").start();
        new Consumer(store, "China").start();
        new Consumer(store, "Maria").start();

	 /*	Producer producer = new Producer(store);
		Consumer consumer = new Consumer(store);
		new Thread(producer).start();
		new Thread(consumer).start();*/

    }
}
