package by.training.controller.command.getcommand;

import by.training.controller.command.Command;
import by.training.controller.command.CommandName;
import by.training.controller.command.getcommand.impl.*;
import by.training.controller.command.getcommand.impl.WrongRequestGetCommand;
import by.training.controller.command.getcommand.impl.WatchSerialGetCommand;

import java.util.EnumMap;
import java.util.Map;

/**
 * Command provider for GET request.
 */
public final class GetCommandProvider {
    private static final GetCommandProvider INSTANCE = new GetCommandProvider();
    /**
     * Map that contains {@link CommandName} and its corresponding implementation of {@link Command} interface.
     */
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private GetCommandProvider() {
        repository.put(CommandName.INDEX, new IndexGetCommand());
        repository.put(CommandName.SHOW, new ShowGetCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationGetCommand());
        repository.put(CommandName.LOGIN, new LoginGetCommand());
        repository.put(CommandName.LOGOUT, new LogOutGetCommand());
        repository.put(CommandName.SEARCH, new SearchGetCommand());
        repository.put(CommandName.MY_SERIAL, new ViewedSerial());
        repository.put(CommandName.LIKED, new LikedSerialGetCommand());
        repository.put(CommandName.RATING, new MostLikedSerialGetCommand());
        repository.put(CommandName.PROFILE, new ProfileGetCommand());
        repository.put(CommandName.LANGUAGE, new LanguageGetCommand());
        repository.put(CommandName.ADMIN_SERIAL, new EditSerialGetCommand());
        repository.put(CommandName.ADMIN_GENRE, new EditGenreGetCommand());
        repository.put(CommandName.ADMIN_COUNTRY, new EditCountryGetCommand());
        repository.put(CommandName.ADMIN_STUDIO, new EditStudioGetCommand());
        repository.put(CommandName.ADMIN_USER, new EditUserGetCommand());
        repository.put(CommandName.WATCH_SERIAL, new WatchSerialGetCommand());
        repository.put(CommandName.STOP_WATCH_SERIAL, new StopWatchSerialGetCommand());
        repository.put(CommandName.LIKE, new LikeSerialGetCommand());
        repository.put(CommandName.DISLIKE, new DislikeSerialGetCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestGetCommand());
        repository.put(CommandName.ERROR, new ErrorGetCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GetCommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Method for getting a {@link Command} by its {@link String} representation.
     *
     * @param requestURI the {@link String} representation request URI
     * @return {@link Command} and wrong request {@link Command} if request URI incorrect
     */
    public Command getCommand(final String requestURI) {
        try {
            return repository.get(CommandName.valueOf(requestURI.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
