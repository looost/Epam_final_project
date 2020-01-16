package by.training.payment.view;

import by.training.payment.controller.Controller;
import by.training.payment.entity.Payment;

import java.util.Scanner;

public class UserUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String command;
        ViewPayment viewPayment = new ViewPayment();

        Payment basket = new Payment();
        boolean flag = true;

            while (flag) {
                viewPayment.showMarket(controller.getMarket());
                System.out.println("Введите (add/remove) пробел название товара пробел количество товара");
                command = scanner.nextLine();
                controller.getCommand(command).execute(basket, command);
                viewPayment.showBasket(basket);
                viewPayment.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    flag = false;
                }
            }
    }
}
