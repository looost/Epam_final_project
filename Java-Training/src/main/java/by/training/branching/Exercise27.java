package by.training.branching;

import by.training.linearalgorithm.validator.Validator;

public class Exercise27 {
    /*
    Найти max{min(a,b), min(c,d)}
     */
    public static void main(String[] args) {
        System.out.print("Введите a - ");
        double a = Validator.enterDouble();
        System.out.print("Введите b - ");
        double b = Validator.enterDouble();
        System.out.print("Введите c - ");
        double c = Validator.enterDouble();
        System.out.print("Введите d - ");
        double d = Validator.enterDouble();

        double min1 = a >= b ? b : a;
        double min2 = c >= d ? d : c;

        System.out.println("Результат - " + (min1 > min2 ? min1 : min2));
    }
}
