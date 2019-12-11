package by.training.branching;

import java.util.Scanner;

public class Task1 {
    /*
     Составить программу: равны ли два числа а и b?
     */
    public static void main(String[] args) {
        System.out.println("Проверка равенства двух чисел (a и b)");
        System.out.print("Введите a: ");
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        System.out.print("Введите b: ");
        double b = sc.nextDouble();
        if (a == b) {
            System.out.println("Числа " + a + " и " + b + " равны");
        } else {
            System.out.println("Числа " + a + " и " + b + " не равны");
        }
        sc.close();
    }
}
