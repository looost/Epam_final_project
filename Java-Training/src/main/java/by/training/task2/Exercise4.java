package by.training.task2;

import by.training.task1.validator.Validator;

public class Exercise4 {
    /*
     Составить программу: равны ли два числа а и b?
     */
    public static void main(String[] args) {
        System.out.println("Проверка равенства двух чисел (a и b)");
        System.out.print("Введите a: ");
        double a = Validator.enterDouble();
        System.out.print("Введите b: ");
        double b = Validator.enterDouble();
        if (a == b) {
            System.out.println("Числа " + a + " и " + b + " равны");
        } else {
            System.out.println("Числа " + a + " и " + b + " не равны");
        }
    }
}