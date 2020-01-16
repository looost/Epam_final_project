package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Payment;

public class RemoveProductCommand implements Command {
    @Override
    public String execute(Payment payment, String command) {
        try {
            if (command.split(" ").length < 2) {
                return "Не выбран товар для удаления!";
            }
            int length = command.split(" ").length;
            if (length == 2) {
                payment.removeProduct(command.split(" ")[1], 1);
                return "OK";
            } else {
                int count = Integer.parseInt(command.split(" ")[2]);
                payment.removeProduct(command.split(" ")[1], count);
                return "OK";
            }
        } catch (NumberFormatException e) {
            return "Введено не числовое значение для количества продуктов!";
        }
    }
}
