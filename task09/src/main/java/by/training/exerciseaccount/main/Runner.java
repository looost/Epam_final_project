package by.training.exerciseaccount.main;


import by.training.exerciseaccount.ClientCreator;
import by.training.exerciseaccount.controller.Controller;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.view.View;

import java.util.Scanner;

public class Runner {
    private static Client client = ClientCreator.giveClient();
    private static Controller controller = new Controller();
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);
    private static int input;

    public static void main(String[] args) {
        view.showMenu();
        input = validation();
        switch (input) {
            case 1:
                controller.executeInfoBalanceAllAccount(client);
                break;
            case 2:
                controller.executeBalanceAmount(client);
                break;
            case 3:
                controller.executeTotalAmountWithPositiveBalance(client);
                break;
            case 4:
                controller.executeTotalAmountWithNegativeBalance(client);
                break;
            case 5:
                view.subFindMenu();
                input = validation();
                switch (input) {
                    case 1:
                        view.showMessage("Введите минимальное значение счета - ");
                        input = validation();
                        controller.executeFindByMinAmount(client,input);
                        break;
                    default:
                        view.showMessage("Не верное значение!");
                        main(new String[0]);
                }
                break;
            case 6:
                controller.executeSortByBalance(client);
                break;
            case 0:
                view.showMessage("До свидания!");
                System.exit(0);
                break;
            default:
                view.showMessage("Не верное значение!");
                main(new String[0]);
        }
        view.showMessage("Для возврата нажмите 0");
        zeroCheck();
    }

    public static int validation() {
        while (!scanner.hasNextInt()) {
            view.showMessage("Введите целое чисто - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void zeroCheck() {
        input = validation();
        if (input != 0) {
            view.showMessage("Для возврата введите 0");
            zeroCheck();
        }
        main(new String[0]);
    }
}
