package by.training.payment.main;

import by.training.payment.controller.Controller;
import by.training.payment.entity.Payment;
import by.training.payment.servise.PaymentService;
import by.training.payment.view.ViewPayment;

import java.util.Scanner;

public class UserUI {
    private static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller();
    private static String command;
    private static Payment payment = new Payment();
    private static ViewPayment viewPayment = new ViewPayment();
    private static PaymentService service = new PaymentService();

    public static void main(String[] args) {

        run();

    }

    private static void run() {
        viewPayment.availableProduct();
        System.out.println("Введите (add/remove) пробел название товара");
        command = scanner.nextLine();
        controller.getCommand(command).execute(payment, command);
        viewPayment.showBasket(payment, service.totalPrice(payment));
        check();
    }

    private static void check() {
        viewPayment.showMessage("Продолжить покупки или выйти? [Y/N]");
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("Y")) {
            run();
        } else if (command.equalsIgnoreCase("N")) {
            System.exit(0);
        }
        check();
    }
}
