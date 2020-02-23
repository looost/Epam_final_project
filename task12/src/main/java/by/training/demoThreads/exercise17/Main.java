package by.training.demoThreads.exercise17;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction();
        int startPrice = new Random().nextInt(100);
        System.out.println("Start price - " + startPrice);
        for (int i = 0; i < auction.BIDS_NUMBER; i++) {
            Bid thread = new Bid(i, startPrice, auction.getBarrier());
            auction.add(thread);
            thread.start();
        }
    }
}
