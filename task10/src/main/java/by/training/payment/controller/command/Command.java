package by.training.payment.controller.command;

import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;

public interface Command {
    String execute(Market market, Payment payment, String command);
}
