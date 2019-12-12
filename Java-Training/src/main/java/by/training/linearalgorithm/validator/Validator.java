package by.training.linearalgorithm.validator;

import java.util.Scanner;

public class Validator {
    public static double enterDouble() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextDouble()) {
            System.out.print("Введите double: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    public static int enterInt() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        return sc.nextInt();
    }

}
