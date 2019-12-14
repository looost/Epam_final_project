package by.training.task1;

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

import by.training.task1.validator.Validator;

public class Exercise37 {
    public static void main(String[] args) {
        System.out.println("Проверка на верность высказываний");

        System.out.print("Целое число N является четным двузначным числом. Введите N: ");
        int a = Validator.enterInt();
        if (a >= 10 && a <= 99 && a % 2 == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма двух первых цифр заданного четырехзначного" +
                " числа равна сумме двух его последних цифр. Введите число: ");
        a = Validator.enterInt();
        if (a < 1000 || a > 9999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if (((a / 1000) % 10 + (a / 100) % 10) == ((a / 10) % 10 + (a % 10))) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма цифр данного трехзначного числа N является четным числом. Введите число: ");
        a = Validator.enterInt();
        if (a < 100 || a > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if ((((a / 100) % 10) + ((a / 10) % 10) + a % 10) % 2 == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Точка с координатами (х, у) принадлежит части плоскости," +
                " лежащей между прямыми х= т, х= п (т<п). Введите значение x: ");
        int x = Validator.enterInt();

        System.out.print("Введите значение т: ");
        int t = Validator.enterInt();
        System.out.print("Введите значение п (п>т): ");
        int p = Validator.enterInt();
        if (p <= t) {
            throw new IllegalArgumentException("Значение п меньше либо равно т");
        }
        if (x >= t && x <= p) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа. Введите число: ");
        a = Validator.enterInt();
        if (a < 100 || a > 999) {
            throw new IllegalArgumentException("Число в неверном диапозоене");
        }
        if (a * a == (Math.pow((a / 100) % 10, 3)) + (Math.pow((a / 10) % 10, 3)) + (Math.pow(a % 10, 3))) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Треугольник со сторонами а,b,с является равнобедренным. Введите a: ");
        a = Validator.enterInt();
        if (a < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        System.out.print("Введите b: ");
        int b = Validator.enterInt();
        if (b < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        System.out.print("Введите c: ");
        int c = Validator.enterInt();
        if (c < 0) {
            throw new IllegalArgumentException("Значение стороны не может быть меньше или равно 0");
        }
        if (a == b || a == c || b == c) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.print("Сумма каких-либо двух цифр заданного" +
                " трехзначного натурального числа N равна третьей цифре. Введите число: ");
        a = Validator.enterInt();
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

        System.out.print("Заданное число N является степенью числа а" +
                " (показатель степени может находиться в диапазоне от 0 до 4). Введите число N: ");
        int n = Validator.enterInt();
        System.out.print("Введите a: ");
        a = Validator.enterInt();
        if (n == 1 || n == a || n == a*a || n == a*a*a || n == a*a*a*a) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.println("График функции у = ах2 + bх+ с проходит через заданную точку с координатами (m, п).");
        System.out.print("Введите a: ");
        a = Validator.enterInt();
        System.out.print("Введите b: ");
        b = Validator.enterInt();
        System.out.print("Введите c: ");
        c = Validator.enterInt();
        System.out.print("Введите координаты m: ");
        int m = Validator.enterInt();
        System.out.print("Введите координаты n: ");
        n = Validator.enterInt();
        if (n == a*m*m + b*m + c) {
            System.out.println("График функции " + a + "x2+" + b + "x+" + c + " проходит через точку с координатами (" + m + "," + n +")");
        } else {
            System.out.println("График функции " + a + "x^2+" + b + "x+" + c + " не проходит через точку с координатами (" + m + "," + n +")");
        }
    }
}
