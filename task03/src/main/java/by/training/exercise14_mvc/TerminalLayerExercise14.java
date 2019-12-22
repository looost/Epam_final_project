package by.training.exercise14_mvc;

import java.util.Scanner;

public class TerminalLayerExercise14 implements ModelLayerExercise14 {
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
    public double getResult(int n) {
        if (n == 0) {
            throw new IllegalArgumentException("Деление на ноль");
        }
        double summa = 0;
        for (int i = 1; i <= n; i++) {
            summa += 1.0 / i;
        }
        return summa;
    }
}
