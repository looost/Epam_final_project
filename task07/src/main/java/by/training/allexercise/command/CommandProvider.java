package by.training.allexercise.command;

import by.training.allexercise.command.iml.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put("exercise4", new Exercise4Command());
        repository.put("exercise14", new Exercise14Command());
        repository.put("exercise27", new Exercise27Command());
        repository.put("exercise37", new Exercise37Command());
    }

    public Command getCommand(String commandName) {
        return repository.get(commandName);
    }

    public boolean hasCommand(String command) {
        return repository.containsKey(command);
    }
}
