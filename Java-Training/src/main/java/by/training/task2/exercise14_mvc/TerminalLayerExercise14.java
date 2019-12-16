package by.training.task2.exercise14_mvc;


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
    public String getResult(double a, double b) {
        if (a <= 0) {
            throw new IllegalArgumentException("Значение угла не может быть меньше либо равно 0");
        }
        if (b <= 0) {
            throw new IllegalArgumentException("Значение угла не может быть меньше либо равно 0");
        }
        double c = 180 - (a + b);
        if (a + b >= 180) {
            return "Такого треугольника не существует";
        } else if (a == 90 || b == 90 || c == 90) {
            return "Треугольник прямоугольный";
        } else {
            return "Треугоьник существует, но он не прямоугольный";
        }
    }
}
