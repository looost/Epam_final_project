package by.training.exercise27;

/*
 В числовой матрице поменять местами два столбца любых столбца, т. е. все элементы одного столбца поставить на
соответствующие им позиции другого, а его элементы второго переместить в первый. Номера столбцов вводит
пользователь с клавиатуры.
 */


import by.training.RandomMatrixCreator;
import by.training.View;

import java.util.Scanner;

public class Runner {
    private static Scanner scanner = new Scanner(System.in);
    private static View view = new View();

    private static int checkValue() {
        while (!scanner.hasNextInt()) {
            view.showMessage("Введите целое число - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Contorller contorller = new Contorller();
        int n;
        int m;
        int column1;
        int column2;
        view.showMessage("Введите кол-во строк - ");
        n = checkValue();
        view.showMessage("Введите кол-во столбцов - ");
        m = checkValue();
        view.showMessage("Введите первый столбец для обмена - ");
        column1 = checkValue();
        view.showMessage("Введите второй столбец для обмена - ");
        column2 = checkValue();
        view.showMessage("Оригинальная матрица - ");
        int [][] arr = RandomMatrixCreator.randomMatrix(n,m);
        view.showMatrix(arr);
        view.showMessage("Матрица, где столбцы " + column1 + " и " + column2 + " поменяли местами - ");
        view.showMatrix(contorller.execute(arr,column1,column2));
    }

}
