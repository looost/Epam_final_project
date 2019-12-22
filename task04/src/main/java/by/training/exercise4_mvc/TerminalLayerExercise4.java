package by.training.exercise4_mvc;

import java.util.Scanner;

public class TerminalLayerExercise4 implements ModelLayerExercise4 {

    @Override
    public int getTerminalInt(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите значение " + message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        return sc.nextInt();
    }

    @Override
    public int getResult(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException();
        }
        int max = Math.max(Math.max(a, b), c);
        for (int i = 2; i < max; i++) {
            if (a % i == 0 && b % i == 0 && c % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
