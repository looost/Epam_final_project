package by.training.exercise9_mvc;

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
        return (isPrime(a, b) && isPrime(a, c) && isPrime(b, c));
    }

    @Override
    public boolean isPrime(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        while (min != 0) {
            int emp = max % min;
            max = min;
            min = emp;
        }
        return max == 1;
    }
}
