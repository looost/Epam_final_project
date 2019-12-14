package by.training.task1.exercise37_pack;

import java.util.Scanner;

public class TerminalLayerExercise37 implements ModelLayerExercise37 {
    @Override
    public double getTerminalDateDouble(String value) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите " + value + ": ");
        while (!sc.hasNextDouble()) {
            System.out.print("Введите double: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    @Override
    public int getTerminalDateInt(String value) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите " + value + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        return sc.nextInt();
    }

    @Override
    public boolean getResultInTask1(int n) {
        return n >= 10 && n <= 99 && n % 2 == 0;
    }

    @Override
    public boolean getResultInTask2(int n) {
        if (n < 1000 || n > 9999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        return ((((n / 1000) % 10) + ((n / 100) % 10)) == (((n / 10) % 10) + (n % 10)));
    }

    @Override
    public boolean getResultInTask3(int n) {
        if (n < 100 || n > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        return (((n / 100) % 10) + ((n / 10) % 10) + n % 10) % 2 == 0;
    }

    @Override
    public boolean getResultInTask4(double x, double y, double t, double p) {
        if (p <= t) {
            throw new IllegalArgumentException("Значение п меньше либо равно т");
        }
        return x >= t && x <= p;
    }

    @Override
    public boolean getResultInTask5(int n) {
        if (n < 100 || n > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        return n * n == (Math.pow((n / 100) % 10, 3)) + (Math.pow((n / 10) % 10, 3)) + (Math.pow(n % 10, 3));
    }

    @Override
    public boolean getResultInTask6(double a, double b, double c) {
        if (a < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        if (b < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        if (c < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        if (a > c + b || b > a + c || c > a + b) {
            throw new IllegalArgumentException("Значение стороны не может быть больше суммы двух других");
        }
        return a == b || a == c || b == c;
    }

    @Override
    public boolean getResultInTask7(int n) {
        if (n < 100 || n > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        int first_number = (n / 100) % 10;
        int second_number = (n / 10) % 10;
        int thrid_number = n % 10;
        return first_number + second_number == thrid_number || first_number + thrid_number == second_number
                || second_number + thrid_number == first_number;
    }

    @Override
    public boolean getResultInTask8(int n, int a) {
        return n == 1 || n == a || n == a * a || n == a * a * a || n == a * a * a * a;
    }

    @Override
    public boolean getResultInTask9(double a, double b, double c, double m, double n) {
        return n == a * m * m + b * m + c;
    }
}

