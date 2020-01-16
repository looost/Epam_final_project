package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Payment;

public class RemoveProductCommand implements Command {
    @Override
    public boolean execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            System.err.println("Не выбран товар для удаления!");
            return false;
        }

        int length = command.split(" ").length;
        if (length == 2) {
            return payment.removeProduct(command.split(" ")[1], 1);
        } else if (length == 3) {
            int count = Integer.parseInt(command.split(" ")[2]);
            payment.removeProduct(command.split(" ")[1], count);
            return true;
        }
        return false;
    }
}
