package by.training.payment.command.impl;

import by.training.payment.command.Command;
import by.training.payment.entity.Payment;

public class WrongRequestCommand implements Command {
    @Override
    public boolean execute(Payment payment, String command) {
        return false;
    }
}
