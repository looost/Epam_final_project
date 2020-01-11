package by.training.payment.command;

import by.training.payment.command.impl.AddProductCommand;
import by.training.payment.command.impl.RemoveProductCommand;
import by.training.payment.command.impl.WrongRequestCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.ADD, new AddProductCommand());
        repository.put(CommandName.REMOVE, new RemoveProductCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    public Command getCommand(String commandName) {
        try {
            return repository.get(CommandName.valueOf(commandName.split(" ")[0].toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Неверная команда!");
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
