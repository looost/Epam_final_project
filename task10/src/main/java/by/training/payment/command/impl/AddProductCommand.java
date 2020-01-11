package by.training.payment.command.impl;

import by.training.payment.command.Command;
import by.training.payment.entity.Payment;
import by.training.payment.exception.PaymentException;

public class AddProductCommand implements Command {
    @Override
    public boolean execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            System.err.println("Не выбран товар для добавления!");
            return false;
        }
        try {
            payment.addProduct(command.split(" ")[1]);
            return true;
        } catch (PaymentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
