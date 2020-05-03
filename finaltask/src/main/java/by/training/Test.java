package by.training;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Test extends Thread {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(UUID.randomUUID().toString().length());
        }
    }
}

