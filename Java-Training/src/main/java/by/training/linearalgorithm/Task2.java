package by.training.linearalgorithm;

import java.util.Scanner;

/*
Вычислить длину окружности и площадь круга одного и того же заданного радиуса R.
 */

public class Task2 {
    private static final double PI = 3.1415;

    public static void main(String[] args) {
        System.out.println("Расчет длинны окружности и площади круга одного и того же радиуса");
        System.out.print("Введите радиус круга - ");
        Scanner sc = new Scanner(System.in);
        double r = sc.nextDouble();
        System.out.println("Длинна окружности радиуса " + r + " равна - " + 2 * PI * r);
        System.out.println("Площадь круга радиуса " + r + " равна - " + PI * r * r);
        sc.close();
    }
}
