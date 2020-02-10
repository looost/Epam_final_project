package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.composite.Component;

public class OriginalTextCommand implements Command {
    @Override
    public CommandResponse execute(Component component, String request) {
        return new CommandResponse(component, "OK");
    }
}
