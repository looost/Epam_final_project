package by.training.controller.command;

import by.training.controller.command.impl.IndexCommand;
import by.training.controller.command.impl.ShowCommand;
import by.training.controller.command.impl.WrongRequestCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private CommandProvider() {
        repository.put(CommandName.INDEX, new IndexCommand());
        repository.put(CommandName.SHOW, new ShowCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String requestURI) {
        try {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}