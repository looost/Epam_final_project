package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

public class WrongRequestCommand implements Command {
    @Override
    public Component execute(Component component, String request) {
        return new Composite(Type.TEXT);
    }
}
