package by.training.dragon.main;

import by.training.dragon.command.CommandProvider;
import by.training.dragon.view.ViewConsole;

import java.util.Scanner;

public class UserUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static String request;
    private static final CommandProvider provider = new CommandProvider();
    private static final ViewConsole view = new ViewConsole();


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        view.showCommand();
        request = scanner.nextLine();
        view.showTreasure(provider.getCommand(request).execute(request));
        continueOrExit();
    }

    public static void continueOrExit() {
        System.out.println();
        view.showMessage("Что бы вернуться, введите что угодно, exit - для выхода");
        request = scanner.nextLine();
        if (request.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        run();
    }
}
