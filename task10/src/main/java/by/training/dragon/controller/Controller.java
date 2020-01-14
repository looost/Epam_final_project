package by.training.dragon.controller;

import by.training.dragon.controller.command.Command;
import by.training.dragon.controller.command.CommandProvider;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Command getCommand(String request) {
        return provider.getCommand(request);
    }
}
