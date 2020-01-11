package by.training.payment.command;

import by.training.payment.entity.Payment;

public interface Command {
    boolean execute(Payment payment, String command);
}
