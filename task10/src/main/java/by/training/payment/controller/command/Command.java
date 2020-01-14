package by.training.payment.controller.command;

import by.training.payment.entity.Payment;

public interface Command {
    boolean execute(Payment payment, String command);
}
