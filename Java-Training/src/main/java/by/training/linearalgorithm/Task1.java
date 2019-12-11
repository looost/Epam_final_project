package by.training.linearalgorithm;

import java.util.Scanner;

/*
Найдите значение функции: z = ( (a – 3 ) * b / 2) + c.
 */

public class Task1 {
    public static void main(String[] args) {
        System.out.println("Решение уравнения вида - ((a-3)*b/2) + c");
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите a: ");
        double a = sc.nextDouble();
        System.out.print("Введите b: ");
        double b = sc.nextDouble();
        System.out.print("Введите c: ");
        double c = sc.nextDouble();
        System.out.println("Результат - " + (((a - 3) * b / 2) + c));
        sc.close();
    }
}

