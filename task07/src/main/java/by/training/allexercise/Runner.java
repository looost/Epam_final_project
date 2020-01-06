package by.training.allexercise;

import by.training.View;

import java.util.Scanner;

public class Runner {
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller();

    static int validate() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целое число - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static void zeroCheck() {
        int value = validate();
        if (value != 0) {
            view.showMessage("Введите 0 для возврата");
            zeroCheck();
        }
        main(new String[0]);
    }

    public static void main(String[] args) {
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
                    arr = MatrixService.randomMatrix(n,m);
                    controller.executeExercise4(arr);
                } catch (IllegalArgumentException e) {
                    view.showMessage(e.getMessage());
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
                    arr = MatrixService.randomMatrix(n,m);
                    controller.executeExercise27(arr,column1,column2);
                } catch (IllegalArgumentException e) {
                    view.showMessage(e.getMessage());
                }
                break;
            case 4:
                view.showTaskExercise37();
                view.showMessage("Введите кол-во строк - ");
                n = validate();
                view.showMessage("Введите кол-во столбцов - ");
                m = validate();
                try {
                    arr = MatrixService.randomMatrix(n,m);
                    controller.executeExercise37(arr);
                } catch (IllegalArgumentException e) {
                    view.showMessage(e.getMessage());
                }
                break;
            case 0:
                scanner.close();
                break;
            default:
                view.showMessage("Неверное значение!");
                main(new String[0]);
        }
        view.showMessage("Для возврата нажмите 0");
        zeroCheck();
    }
}
