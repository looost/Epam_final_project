package by.training.payment.view;

import by.training.payment.controller.Controller;
import by.training.payment.entity.Payment;
import by.training.payment.servise.PaymentService;
import by.training.payment.servise.exeception.ServiceException;
import by.training.payment.view.ViewPayment;

import java.util.Scanner;

public class UserUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String command;
        ViewPayment viewPayment = new ViewPayment();
        PaymentService service = new PaymentService();

        Payment payment = new Payment();
        boolean flag = true;

        try {
            while (flag) {
                viewPayment.availableProduct(service.getPayment());
                System.out.println("Введите (add/remove) пробел название товара");
                command = scanner.nextLine();
                controller.getCommand(command).execute(payment, command);
                viewPayment.showBasket(payment, service.totalPrice(payment));
                viewPayment.showMessage("--> Что бы вернуться, введите что угодно, exit - для выхода");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    flag = false;
                }
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
