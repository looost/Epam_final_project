package by.training.task1;

import by.training.task1.validator.Validator;

/*
Дано значение a. Не используя никаких функций и никаких операций, кроме умножения,
получить значение а в 8 за три операции и а в 10 за четыре операции.
*/
public class Exercise27 {
    public static void main(String[] args) {
        System.out.print("Введите a - ");
        double a = Validator.enterDouble();
        double ain2 = a * a;
        double ain4 = ain2 * ain2;
        double ain8 = ain4 * ain4;
        System.out.println("Значение a в 8 степени за три операции - " + ain8);
        System.out.print("Введите a - ");
        a = Validator.enterDouble();
        ain2 = a * a;
        ain4 = ain2 * ain2;
        double ain6 = ain4 * ain2;
        double ain10 = ain6 * ain4;
        System.out.println("Значение a в 10 степени за четыре операции - " + ain10);
    }
}
