package by.training.controller.command;

import by.training.entity.composite.Component;

public class CommandResponse {
    private final Component component;
    private final String message;

    public CommandResponse(Component component, String message) {
        this.component = component;
        this.message = message;
    }

    public Component getComponent() {
        return component;
    }

    public String getMessage() {
        return message;
    }
}
