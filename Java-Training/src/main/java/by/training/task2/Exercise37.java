package by.training.task2;

import by.training.task1.validator.Validator;

public class Exercise37 {
    /*
    Вычеслить значение функции:
    F(x) = -x^2 + 3x + 9 , если x>=3;
    F(x) = 1/(x^3 - 6) , если x<3;
     */

    public static void main(String[] args) {
        System.out.print("Введите x - ");
        double x = Validator.enterDouble();
        if (x >= 3) {
            System.out.println("F(" + x + ") = " + (Math.pow(x, 2) + 3 * x + 9));
        } else {
            System.out.println("F(" + x + ") = " + 1 / (Math.pow(x, 3) - 6));
        }

    }

}
