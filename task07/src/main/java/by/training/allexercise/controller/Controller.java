package by.training.allexercise.controller;

import by.training.allexercise.command.CommandProvider;
import by.training.allexercise.entity.Matrix;

import java.util.Map;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public void doAction(String request) {
        String command = request.split(" ")[0];
        provider.getCommand(command).execute(request);
    }

    public CommandProvider getProvider() {
        return provider;
    }
}
