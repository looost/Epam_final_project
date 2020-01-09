package by.training.allexercise;

import by.training.view.View;
import by.training.allexercise.controller.Controller;
import by.training.allexercise.creator.RandomMatrixCreator;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;

import java.util.Scanner;

public class Runner {
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller();
    private static Matrix matrix;

    private static int validate() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целое число - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void zeroCheck() {
        int value = validate();
        if (value != 0) {
            view.showMessage("Введите 0 для возврата");
            zeroCheck();
        }
        run();
    }

    private static void run() {
        int n;
        int m;
        int [][] arr;
        view.showMenu();
        int choice = validate();
        switch (choice) {
            case 1:
                view.showTaskExercise4();
                view.showMessage("Введите кол-во строк - ");
                n = validate();
                view.showMessage("Введите кол-во столбцов - ");
                m = validate();
                try {
                    matrix = new Matrix(n, m);
                    RandomMatrixCreator.fillRandomValue(matrix);
                    controller.executeExercise4(matrix);
                } catch (MatrixException e) {
                    view.showMessage("Неверные значения!");
                }
                break;
            case 2:
                view.showTaskExercise14();
                view.showMessage("Введите размерность матрицы: ");
                n = validate();
                controller.executeExercise14(n);
                break;
            case 3:
                view.showTaskExercise27();
                view.showMessage("Введите кол-во строк - ");
                n = validate();
                view.showMessage("Введите кол-во столбцов - ");
                m = validate();
                view.showMessage("Введите номер первого столбца для обмена - ");
                int column1 = validate();
                view.showMessage("Введите номер втрого столбца для обмена - ");
                int column2 = validate();
                try {
                    matrix = new Matrix(n, m);
                    RandomMatrixCreator.fillRandomValue(matrix);
                    controller.executeExercise27(matrix, column1, column2);
                } catch (MatrixException e) {
                    view.showMessage("Неверные значения!");
                }

                break;
            case 4:
                view.showTaskExercise37();
                view.showMessage("Введите кол-во строк - ");
                n = validate();
                view.showMessage("Введите кол-во столбцов - ");
                m = validate();
                try {
                    matrix = new Matrix(n, m);
                    RandomMatrixCreator.fillRandomValue(matrix);
                    controller.executeExercise37(matrix);
                } catch (MatrixException e) {
                    view.showMessage("Неверные значения!");
                }
                break;
            case 0:
                view.showMessage("До свидания!");
                System.exit(0);
                break;
            default:
                view.showMessage("Неверное значение!");
                run();
        }
        view.showMessage("Для возврата нажмите 0");
        zeroCheck();
    }


    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }
}
