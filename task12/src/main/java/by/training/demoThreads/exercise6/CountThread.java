package by.training.demoThreads.exercise6;

class CountThread implements Runnable {

    CommonResource1 res;

    CountThread(CommonResource1 res1) {
        this.res = res1;
    }

    public void run() {
        res.increment();
//        synchronized (res) {
//            res.x = 1;
//            for (int i = 1; i < 5; i++) {
//                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
//                res.x++;
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                }
//            }
//            System.out.println("-----");
//        }
    }
}

