package by.training.exercise14_mvc;

import java.util.Scanner;

public class TerminalLayerExercise14 implements ModelLayerExercise14 {
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
    public double getResultArea(double r) {
        return Math.PI * r * r;
    }

    @Override
    public double getResultLength(double r) {
        return 2 * Math.PI * r;
    }
}
