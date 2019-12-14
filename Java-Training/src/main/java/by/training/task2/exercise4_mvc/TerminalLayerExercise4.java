package by.training.task2.exercise4_mvc;

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
    public boolean getResult(double a, double b) {
        return a == b;
    }
}
