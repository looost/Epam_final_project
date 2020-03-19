package by.training.controller.command;

import by.training.controller.command.impl.*;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private CommandProvider() {
        repository.put(CommandName.INDEX, new IndexCommand());
        repository.put(CommandName.SHOW, new ShowCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.REGISTR, new RegistrCommand());
        repository.put(CommandName.LOGIN, new LoginCommand());
        repository.put(CommandName.LOGINPOST, new LoginPostCommand());
        repository.put(CommandName.LOGOUT, new LogOutCommand());
        repository.put(CommandName.SEARCH, new SearchCommand());
        repository.put(CommandName.ADD_COMMENT, new AddCommentCommand());
        repository.put(CommandName.PROFILE, new ProfileCommand());
        repository.put(CommandName.PROFILEPOST, new ProfilePostCommand());
        repository.put(CommandName.LANGUAGE, new LanguageCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String requestURI) {
        try {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
