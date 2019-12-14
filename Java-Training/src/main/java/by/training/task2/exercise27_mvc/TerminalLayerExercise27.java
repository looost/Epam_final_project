package by.training.task2.exercise27_mvc;

import java.util.Scanner;

public class TerminalLayerExercise27 implements ModelLayerExercise27 {
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
    public double getResult(double a, double b, double c, double d) {
        double min1 = a >= b ? b : a;
        double min2 = c >= d ? d : c;
        return min1 > min2 ? min1 : min2;
    }
}
