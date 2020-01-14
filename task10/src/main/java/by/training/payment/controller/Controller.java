package by.training.payment.controller;

import by.training.payment.controller.command.Command;
import by.training.payment.controller.command.CommandProvider;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Command getCommand(String command) {
        return provider.getCommand(command);
    }
}
