package by.training.exerciseaccount.main;

import by.training.exerciseaccount.controller.Controller;
import by.training.exerciseaccount.view.View;

import java.util.Scanner;

public class UserUI {
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        int request;
        view.showMenu();
        request = validator();
        if (request == 5) {
            view.showMessage("Введите минимальный баланс:");
            request = validator();
            controller.getCommand(5).execute(" " + request);
        } else {
            controller.getCommand(request).execute("");
        }
        checkZero();
    }

    private static void checkZero() {
        if (!scanner.nextLine().equals("0")) {
            view.showMessage("Для возврата введите 0");
            checkZero();
        }
        run();
    }

    private static int validator() {
        while (!scanner.hasNextInt()) {
            view.showMessage("Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
