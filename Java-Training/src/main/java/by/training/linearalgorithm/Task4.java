package by.training.linearalgorithm;

/*
Составить линейную программу, печатающую значение true, если указанное высказывание является истинным, и
false — в противном случае:
• Целое число N является четным двузначным числом.
• Сумма двух первых цифр заданного четырехзначного числа равна сумме двух его последних цифр.
• Сумма цифр данного трехзначного числа N является четным числом.
• Точка с координатами (х, у) принадлежит части плоскости, лежащей между прямыми х= т, х= п (т<п).
• Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа.
• Треугольник со сторонами а,b,с является равнобедренным.
• Сумма каких-либо двух цифр заданного трехзначного натурального числа N равна третьей цифре.
• Заданное число N является степенью числа а (показатель степени может находиться в диапазоне от 0 до 4).
• График функции у = ах2 + bх+ с проходит через заданную точку с координатами (m, п).
 */

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("Проверка на верность высказываний");

        System.out.print("Целое число N является четным двузначным числом. Введите N: ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        if (a >= 10 && a <= 99 && a % 2 == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма двух первых цифр заданного четырехзначного" +
                " числа равна сумме двух его последних цифр. Введите число: ");
        a = sc.nextInt();
        if (a < 1000 || a > 9999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if (((a / 1000) % 10 + (a / 100) % 10) == ((a / 10) % 10 + (a % 10))) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма цифр данного трехзначного числа N является четным числом. Введите число: ");
        a = sc.nextInt();
        if (a < 100 || a > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if ((((a / 100) % 10) + ((a / 10) % 10) + a % 10) % 2 == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Точка с координатами (х, у) принадлежит части плоскости, лежащей между прямыми х= т, х= п (т<п). Введите значение x: ");
        int x = sc.nextInt();
        if (x < 0) {
            throw new IllegalArgumentException("Значение меньше 0");
        }
        System.out.print("Введите значение т: ");
        int t = sc.nextInt();
        System.out.print("Введите значение п (п>т): ");
        int p = sc.nextInt();
        if (p <= t) {
            throw new IllegalArgumentException("Значение п меньше либо равно т");
        }
        if (x >= t && x <= p) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа. Введите число: ");
        a = sc.nextInt();
        if (a < 100 || a > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if (a * a == (Math.pow((a / 100) % 10, 3)) + (Math.pow((a / 10) % 10, 3)) + (Math.pow(a % 10, 3))) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Треугольник со сторонами а,b,с является равнобедренным. Введите a: ");
        a = sc.nextInt();
        if (a <= 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        System.out.print("Введите b: ");
        int b = sc.nextInt();
        if (b <= 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        System.out.print("Введите c: ");
        int c = sc.nextInt();
        if (c <= 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        if (a == b || a == c || b == c) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма каких-либо двух цифр заданного" +
                " трехзначного натурального числа N равна третьей цифре. Введите число: ");
        a = sc.nextInt();
        if (a < 100 || a > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        int first_number = (a / 100) % 10;
        int second_number = (a / 10) % 10;
        int thrid_number = a % 10;
        if (first_number + second_number == thrid_number || first_number + thrid_number == second_number
                || second_number + thrid_number == first_number) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }


    }
}
