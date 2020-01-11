package by.training.payment.command;

import by.training.payment.command.impl.AddProductCommand;
import by.training.payment.command.impl.RemoveProductCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put("ADD", new AddProductCommand());
        repository.put("REMOVE", new RemoveProductCommand());
    }

    public Command getCommand(String commandName) {
        return repository.get(commandName.split(" ")[0].toUpperCase());
    }
}
