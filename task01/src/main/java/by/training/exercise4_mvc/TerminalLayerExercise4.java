package by.training.exercise4_mvc;

import java.util.Scanner;

public class TerminalLayerExercise4 implements ModelLayerExercise4 {
    @Override
    public double getTerminalDate(String value) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите значение " + value + ": ");
        while (!sc.hasNextDouble()) {
            System.out.print("Введите double: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    @Override
    public double getResult(double a, double b, double c) {
        return (((a - 3) * b / 2) + c);
    }
}
