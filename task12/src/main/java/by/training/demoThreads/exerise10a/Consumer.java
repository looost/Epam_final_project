package by.training.demoThreads.exerise10a;

class Consumer extends Thread {//implements Runnable {

    Store store;
    String name;

    Consumer(Store store, String name) {
        this.store = store;
        this.name = name;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            System.out.println(name + " покупает");
            store.get();
        }
    }
}


