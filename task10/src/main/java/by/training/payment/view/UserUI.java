package by.training.payment.view;

import by.training.payment.controller.Controller;
import by.training.payment.entity.Payment;

import java.util.Scanner;

public class UserUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        ViewPayment viewPayment = new ViewPayment();
        String command;
        String response;
        Payment basket = new Payment();
        boolean flag = true;

            while (flag) {
                viewPayment.showMarket(controller.getMarket());
                System.out.println("Введите (add/remove) пробел название товара пробел количество товара");
                command = scanner.nextLine();
                response = controller.getCommand(command).execute(basket, command);
                if (response.equalsIgnoreCase("OK")) {
                    viewPayment.showBasket(basket);
                } else {
                    viewPayment.showError(response);
                }
                viewPayment.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    flag = false;
                }
            }
    }
}
