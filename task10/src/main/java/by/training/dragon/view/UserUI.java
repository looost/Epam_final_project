package by.training.dragon.view;

import by.training.dragon.controller.Controller;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.List;
import java.util.Scanner;

public class UserUI {


    public static void main(String[] args) {
        Controller controller = new Controller();
        ViewConsole view = new ViewConsole();
        Scanner scanner = new Scanner(System.in);
        String request;
        String response;
        List<Treasure> treasureList;
        boolean flag = true;

        while (flag) {
            view.showCommand();
            request = scanner.nextLine();
            treasureList = controller.getCommand(request).execute(request);
            response = controller.getCommand(request).getResponse();

            if (response.equalsIgnoreCase("OK")) {
                view.showTreasure(treasureList);
                view.showMessage("Общая ценность сокровищ равна " + ServiceFactory.
                        getInstance().getCaveService().totalAmount(treasureList));
            } else {
                System.err.println(response);
            }

            view.showMessage("\n--> Что бы вернуться, введите что угодно, exit - для выхода");
            request = scanner.nextLine();
            if (request.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    }
}
