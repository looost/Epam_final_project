package by.training.task3.exercise27_mvc;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalLayerExercise27 implements ModelLayerExercise27 {
    @Override
    public int getTerminalDate(String value) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите " + value + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите " + value + ": ");
            sc.next();
        }
        return sc.nextInt();
    }

    @Override
    public ArrayList<Integer> getResult(int m, int n) {
        if (m > n) {
            throw new IllegalArgumentException("m>n");
        }
        ArrayList<Integer> arr = new ArrayList<>();
        while (m <= n) {
            for (int i = 2; i < m; i++) {
                if (m % i == 0) {
                    arr.add(i);
                }
            }
            m++;
        }
        return arr;
    }
}
