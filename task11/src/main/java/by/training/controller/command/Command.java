package by.training.controller.command;

import by.training.entity.composite.Component;

public interface Command {
    CommandResponse execute(Component component, String request);
}
