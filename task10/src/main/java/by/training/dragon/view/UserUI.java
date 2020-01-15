package by.training.dragon.view;

import by.training.dragon.controller.command.CommandProvider;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.List;
import java.util.Scanner;

public class UserUI {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String request;
        CommandProvider provider = new CommandProvider();
        ViewConsole view = new ViewConsole();
        List<Treasure> treasureList;
        boolean flag = true;

        while (flag) {
            view.showCommand();
            request = scanner.nextLine();
            treasureList = provider.getCommand(request).execute(request);
            view.showTreasure(treasureList);
            view.showMessage("Общая ценность сокровищ равна " + ServiceFactory.
                    getInstance().getCaveService().totalAmount(treasureList));
            view.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
            request = scanner.nextLine();
            if (request.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    }
}
