package by.training.dragon.view;

import by.training.dragon.controller.command.CommandProvider;

import java.util.Scanner;

public class UserUI {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String request;
        CommandProvider provider = new CommandProvider();
        ViewConsole view = new ViewConsole();
        boolean flag = true;

        while (flag) {
            view.showCommand();
            request = scanner.nextLine();
            view.showTreasure(provider.getCommand(request).execute(request));
            view.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
            request = scanner.nextLine();
            if (request.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    }
}
