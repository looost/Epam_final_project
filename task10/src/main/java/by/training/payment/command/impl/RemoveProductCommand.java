package by.training.payment.command.impl;

import by.training.payment.command.Command;
import by.training.payment.entity.Payment;

public class RemoveProductCommand implements Command {
    @Override
    public boolean execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            System.err.println("Не выбран товар для добавления!");
            return false;
        }
        return payment.removeProduct(command.split(" ")[1]);
    }
}
