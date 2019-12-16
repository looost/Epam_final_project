package by.training.task4.exercise9_mvc;

import java.util.Scanner;

public class TerminalDateExercise9 implements ModelLayerExercise9 {
    @Override
    public int getTerminalDate(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите значение " + message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        return sc.nextInt();
    }

    @Override
    public boolean getResult(int a, int b, int c) {
        int max = Math.max(Math.max(a, b), c);
        for (int i = 2; i < max; i++) {
            if (a % i == 0 || b % i == 0 || c % i == 0 && (i != a && i != b && i != c)) {
                return true;
            }
        }
        return false;
    }
}
