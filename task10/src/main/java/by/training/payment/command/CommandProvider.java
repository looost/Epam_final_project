package by.training.payment.command;

import by.training.payment.command.impl.AddProductCommand;
import by.training.payment.command.impl.RemoveProductCommand;
import by.training.payment.command.impl.WrongRequestCommand;

import static by.training.payment.command.CommandName.*;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(ADD, new AddProductCommand());
        repository.put(REMOVE, new RemoveProductCommand());
        repository.put(WRONG_REQUEST, new WrongRequestCommand());
    }

    public Command getCommand(String commandName) {
        try {
            return repository.get(CommandName.valueOf(commandName.split(" ")[0].toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Неверная команда!");
            return repository.get(WRONG_REQUEST);
        }
    }
}
