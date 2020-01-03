package by.training.exercise37;

import by.training.RandomMatrixCreator;
import by.training.View;

import java.util.Scanner;

public class Runner {
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);

    private static int checkValue() {
        while (!scanner.hasNextInt()) {
            view.showMessage("Введите целое число - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        int n;
        int m;
        System.out.println("Введите число строк - ");
        n = checkValue();
        System.out.println("Введите число столбцов - ");
        m = checkValue();
        System.out.println("Оригинальная матрица - ");
        int [][] arr = RandomMatrixCreator.randomMatrix(n,m);
        view.showMatrix(arr);
        System.out.println("Матрица, где строки случайным образом поменяли местами");
        view.showMatrix(controller.execute(arr));
    }

}
