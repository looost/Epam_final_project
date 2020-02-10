package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

public class WrongRequestCommand implements Command {
    @Override
    public CommandResponse execute(Component component, String request) {
        return new CommandResponse(new Composite(Type.TEXT), "Неверная команда!");
    }
}
