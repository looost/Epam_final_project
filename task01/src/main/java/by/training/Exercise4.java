package by.training;

import by.training.validator.Validator;

/*
Найдите значение функции: z = ( (a – 3 ) * b / 2) + c.
 */

public class Exercise4 {
    public static void main(String[] args) {
        System.out.println("Решение уравнения вида - ((a-3)*b/2) + c");
        System.out.print("Введите a: ");
        double a = Validator.enterDouble();
        System.out.print("Введите b: ");
        double b = Validator.enterDouble();
        System.out.print("Введите c: ");
        double c = Validator.enterDouble();
        System.out.println("Результат - " + (((a - 3) * b / 2) + c));
    }
}

