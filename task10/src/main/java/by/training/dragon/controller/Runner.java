package by.training.dragon.controller;

import by.training.dragon.controller.command.CommandResponse;
import by.training.dragon.view.UserUI;
import by.training.dragon.view.ViewConsole;

public class Runner {
    public static void main(String[] args) {
        ViewConsole view = new ViewConsole();
        UserUI userUI = new UserUI();
        Controller controller = new Controller();
        String request;
        CommandResponse response;
        boolean flag = true;

        while (flag) {
            view.showCommand();
            request = userUI.enterString();
            response = controller.getCommand(request).execute(request);
            if (response.getStatus().equalsIgnoreCase("OK")) {
                view.showTreasure(response.getTreasureList());
                view.showMessage("\nОбщая ценность сокровищ равна " + controller.totalAmount(response.getTreasureList()));
            } else {
                view.showError(response.getStatus());
            }
            view.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
            request = userUI.enterString();
            if (request.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    }
}