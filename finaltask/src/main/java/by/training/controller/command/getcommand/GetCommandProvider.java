package by.training.controller.command.getcommand;

import by.training.controller.command.Command;
import by.training.controller.command.CommandName;
import by.training.controller.command.getcommand.impl.*;
import by.training.controller.command.getcommand.impl.WrongRequestGetCommand;
import by.training.controller.command.getcommand.impl.WatchSerialGetCommand;

import java.util.EnumMap;
import java.util.Map;

public class GetCommandProvider {
    private static final GetCommandProvider instance = new GetCommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private GetCommandProvider() {
        repository.put(CommandName.INDEX, new IndexGetCommand());
        repository.put(CommandName.SHOW, new ShowGetCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationGetCommand());
        repository.put(CommandName.LOGIN, new LoginGetCommand());
        repository.put(CommandName.LOGOUT, new LogOutGetCommand());
        repository.put(CommandName.SEARCH, new SearchGetCommand());
        repository.put(CommandName.MY_SERIAL, new ViewedSerial());
        repository.put(CommandName.PROFILE, new ProfileGetCommand());
        repository.put(CommandName.LANGUAGE, new LanguageGetCommand());
        repository.put(CommandName.ADMIN_SERIAL, new EditSerialGetCommand());
        repository.put(CommandName.ADMIN_GENRE, new EditGenreGetCommand());
        repository.put(CommandName.ADMIN_COUNTRY, new EditCountryGetCommand());
        repository.put(CommandName.ADMIN_STUDIO, new EditStudioGetCommand());
        repository.put(CommandName.WATCH_SERIAL, new WatchSerialGetCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestGetCommand());
    }

    public static GetCommandProvider getInstance() {
        return instance;
    }

    private boolean contains(String requestURI) {
        try {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase())) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Command getCommand(String requestURI) {
        if (contains(requestURI)) {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase()));
        } else {
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
