package by.training.allexercise.command;

import by.training.allexercise.command.iml.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.EXERCISE4, new Exercise4Command());
        repository.put(CommandName.EXERCISE14, new Exercise14Command());
        repository.put(CommandName.EXERCISE27, new Exercise27Command());
        repository.put(CommandName.EXERCISE37, new Exercise37Command());
    }

    public Command getCommand(String commandName) {
        return repository.get(CommandName.valueOf(commandName.toUpperCase()));
    }

    public boolean hasCommand(String command) {
        if (!CommandName.contains(command)) {
            return false;
        }
        return repository.containsKey(CommandName.valueOf(command.toUpperCase()));
    }
}
