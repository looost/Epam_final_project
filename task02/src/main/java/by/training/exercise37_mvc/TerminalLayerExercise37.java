package by.training.exercise37_mvc;

import java.util.Scanner;

public class TerminalLayerExercise37 implements ModelLayerExercise37 {
    @Override
    public double getTerminalDate(String value) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите " + value + ": ");
        while (!sc.hasNextDouble()) {
            System.out.print("Введите double: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    @Override
    public double getResult(double x) {
        return x >= 3 ? (Math.pow(x, 2) + 3 * x + 9) : (1 / (Math.pow(x, 3) - 6));
    }
}

