package by.training.allexercise.main;

import by.training.allexercise.controller.Controller;
import by.training.view.View;


import java.util.Scanner;

public class UserUI {
    private static Scanner scanner = new Scanner(System.in);
    private static View view = new View();
    private static Controller controller = new Controller();


    public static void main(String[] args) {
        run();
    }

    private static void run() {
        view.task();
        String command = scanner.nextLine();
        String exercise = command.split(" ")[0];
        if (controller.getProvider().hasCommand(exercise)) {
            if (exercise.equals("exercise4")) {
                view.showTaskExercise4();
                view.showMessage("Введите число строк и столбцов через пробел: ");
                command = scanner.nextLine();
                controller.doAction("exercise4 " + command);
            } else if (exercise.equals("exercise14")) {
                view.showTaskExercise14();
                view.showMessage("Введите размерность матрицы:");
                command = scanner.nextLine();
                controller.doAction("exercise14 " + command);
            } else if (exercise.equals("exercise27")) {
                view.showTaskExercise27();
                view.showMessage("Введите через пробел число строк, столбцов, номер первого столбца для обмена и номер второго столбца для обмена");
                command = scanner.nextLine();
                controller.doAction("exercise27 " + command);
            } else if (exercise.equals("exercise37")) {
                view.showTaskExercise37();
                view.showMessage("Введите число строк и столбцов через пробел: ");
                command = scanner.nextLine();
                controller.doAction("exercise37 " + command);
            }
            view.showMessage("Для возврата введите 0");
            zeroCheck();
        } else {
            view.showMessage("Неверная команда");
            run();
        }

    }


    private static void zeroCheck() {
        if (!scanner.nextLine().equals("0")) {
            view.showMessage("Для возврата введите 0");
            zeroCheck();
        }
        run();
    }

}
