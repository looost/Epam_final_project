package by.training.exercise27_mvc;

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
    public double getResultIn3Operation(double a) {
        double ain2 = a * a;
        double ain4 = ain2 * ain2;
        double ain8 = ain4 * ain4;
        return ain8;
    }

    @Override
    public double getResultIn4Operation(double a) {
        double ain2 = a * a;
        double ain4 = ain2 * ain2;
        double ain6 = ain4 * ain2;
        double ain10 = ain6 * ain4;
        return ain10;
    }
}
