package by.training.task3;

import by.training.task1.validator.Validator;

public class Exercise27 {
    /*
    Для каждого натурального числа в промежутке от m до n вывести все делители, кроме единицы и
    самого числа. m и n вводятся с клавиатуры
     */

    public static void main(String[] args) {
        System.out.print("Введите m: ");
        int m = Validator.enterInt();
        System.out.print("Введите n: ");
        int n = Validator.enterInt();
        while (m <= n) {
            for (int i = 2; i < m; i++) {
                if (m % i == 0) {
                    System.out.println(m + " делиться без остатка на " + i);
                }
            }
            m++;
        }

    }
}
