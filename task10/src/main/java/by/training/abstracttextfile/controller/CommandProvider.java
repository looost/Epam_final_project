package by.training.abstracttextfile.controller;

import by.training.abstracttextfile.controller.impl.*;

import java.util.EnumMap;
import java.util.Map;

import static by.training.abstracttextfile.controller.CommandName.*;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private CommandProvider() {
        repository.put(DIR, new CreateDirectoryCommand());
        repository.put(FILE, new CreateFileCommand());
        repository.put(TEXT_FILE, new CreateTextFileCommand());
        repository.put(RENAME_DIR, new RenameDirectoryImpl());
        repository.put(WRONG_REQUEST, new WrongRequestCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    Command getCommand(String command) {
        try {
            return repository.get(CommandName.valueOf(command.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return repository.get(WRONG_REQUEST);
        }
    }
}
