package by.training.linearalgorithm;

import java.util.Scanner;

/*
Дано значение a. Не используя никаких функций и никаких операций, кроме умножения,
получить значение а 8 за три операции и а 10 за четыре операции.
*/
public class Task3 {
    public static void main(String[] args) {
        System.out.print("Введите a - ");
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double ain2 = a * a;
        double ain4 = ain2 * ain2;
        double ain8 = ain4 * ain2;
        System.out.println("Значение a в 8 " + ain8);
        System.out.print("Введите a - ");
        a = sc.nextDouble();
        ain2 = a * a;
        ain4 = ain2 * ain2;
        ain8 = ain4 * ain2;

    }
}
