package by.training;

/*
Даны два угла треугольника (в градусах). Определить, существует ли такой треугольник, и если да, то будет ли он
прямоугольным.
 */

import by.training.validator.Validator;

public class Exercise14 {
    public static void main(String[] args) {
        System.out.println("Проверка: существует ли такой треуголник по трем углам. Если да, то является ли он прямоугольным");
        System.out.print("Введите значение первого угла: ");
        double a = Validator.enterDouble();
        if (a <= 0) {
            throw new IllegalArgumentException("Значение угла не может быть меньше либо равно 0");
        }
        System.out.print("Введите значение второго угла: ");
        double b = Validator.enterDouble();
        if (b <= 0) {
            throw new IllegalArgumentException("Значение угла не может быть меньше либо равно 0");
        }
        double c = 180 - (a + b);
        if (a + b >= 180) {
            System.out.println("Такого треугольника не существует");
        } else if (a == 90 || b == 90 || c == 90) {
            System.out.println("Треугольник прямоугольный");
        } else {
            System.out.println("Треугольник существует, но он не прямоугольный");
        }
    }
}
