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

        try {
            int count = Integer.parseInt(command.split(" ")[2]);
            for (int i = 0; i < count; i++) {
                payment.removeProduct(command.split(" ")[1]);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return payment.removeProduct(command.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.err.println("Неверно задано количество товара для удаления!");
            return false;
        }

    }
}
