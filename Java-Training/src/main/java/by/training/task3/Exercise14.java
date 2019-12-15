package by.training.task3;

import by.training.task1.validator.Validator;

public class Exercise14 {

    /*
    Дано натуральное n. вычислить: 1 + 1/2 + 1/3 + 1/4 + ... + 1/n
     */
    public static void main(String[] args) {
        System.out.print("Введите n: ");
        int n = Validator.enterInt();
        if (n == 0) {
            throw new IllegalArgumentException("Деление на ноль");
        }
        double summa = 0;
        for (int i = 1; i <= n; i++) {
            summa += 1.0 / i;
        }
        System.out.println(summa);
    }
}
