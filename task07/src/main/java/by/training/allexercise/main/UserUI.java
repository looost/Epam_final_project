package by.training.allexercise.main;

import by.training.allexercise.controller.NewController;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.view.View;


import java.util.Scanner;

public class UserUI {
    private static Scanner scanner = new Scanner(System.in);
    private static View view = new View();

    private static void run() {

        NewController controller = new NewController();


        view.task();
        String command = scanner.nextLine();
        if (controller.getProvider().hasCommand(command.split(" ")[0])) {
            controller.doAction(scanner.nextLine());
        } else {
            view.showMessage("Неверная команда");
            zeroCheck();
        }

    }


    private static void zeroCheck() {
        view.showMessage("Для вовзрата введите 0");
        String zero = scanner.nextLine();
        if (zero.equals("0")) {
            scanner.next();
            zeroCheck();
        }
        run();
    }


    public static void main(String[] args) {
        run();
    }
}
