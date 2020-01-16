package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;

public class WrongRequestCommand implements Command {
    @Override
    public String execute(Market market, Payment payment, String command) {
        return "Не верная команда!";
    }
}
