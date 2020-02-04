package by.training.controller;

import by.training.controller.command.CommandProvider;
import by.training.entity.composite.Component;

public class Controller {
    public Component execute(Component component, String request) {
        CommandProvider provider = new CommandProvider();

        return provider.getCommand(request).execute(component, request);
    }
}
