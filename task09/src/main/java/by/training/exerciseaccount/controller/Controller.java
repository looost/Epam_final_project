package by.training.exerciseaccount.controller;

import by.training.exerciseaccount.command.Command;
import by.training.exerciseaccount.command.CommandProvider;

public class Controller {
    private CommandProvider provider = new CommandProvider();

    public Command getCommand(int value) {
        return provider.getCommand(value);
    }
}
