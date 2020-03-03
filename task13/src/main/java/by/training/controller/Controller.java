package by.training.controller;

import by.training.controller.command.Command;
import by.training.controller.command.CommandProvider;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public Command getCommand(String request) {
        return commandProvider.getCommand(request);
    }
}
