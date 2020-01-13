package by.training.dragon.controller;

import by.training.dragon.command.Command;
import by.training.dragon.command.CommandProvider;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Command getCommand(String request) {
        return provider.getCommand(request);
    }
}
