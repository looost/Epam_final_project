package by.training.task1;

import by.training.task1.validator.Validator;

/*
Вычислить длину окружности и площадь круга одного и того же заданного радиуса R.
 */

public class Exercise14 {

    public static void main(String[] args) {
        System.out.println("Расчет длинны окружности и площади круга одного и того же радиуса");
        System.out.print("Введите радиус круга - ");
        double r = Validator.enterDouble();
        System.out.println("Длинна окружности радиуса " + r + " равна - " + 2 * Math.PI * r);
        System.out.println("Площадь круга радиуса " + r + " равна - " + Math.PI * r * r);
    }
}
