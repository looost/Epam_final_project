package by.training.controller;

import by.training.controller.command.Command;
import by.training.controller.command.CommandProvider;

class Controller {
    Command getCommand(String request) {
        CommandProvider provider = new CommandProvider();
        return provider.getCommand(request);
    }
}
