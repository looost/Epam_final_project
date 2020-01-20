package by.training.payment.controller;

import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;
import by.training.payment.view.UserUI;
import by.training.payment.view.ViewPayment;


public class Runner {
    public static void main(String[] args) {
        ViewPayment viewPayment = new ViewPayment();
        UserUI userUI = new UserUI();
        Controller controller = new Controller();
        String command;
        String response;
        Market market = controller.getMarket();
        Payment basket = new Payment();
        boolean flag = true;

        while (flag) {
            viewPayment.showMarket(market);
            System.out.println("Введите (add/remove) пробел название товара пробел количество товара");
            command = userUI.enterString();
            response = controller.getCommand(command).execute(market, basket, command);
            if (response.equalsIgnoreCase("OK")) {
                viewPayment.showBasket(basket);
            } else {
                viewPayment.showError(response);
            }
            viewPayment.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
            command = userUI.enterString();
            if (command.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    }
}

