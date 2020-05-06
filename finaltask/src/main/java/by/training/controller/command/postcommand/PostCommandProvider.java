package by.training.controller.command.postcommand;

import by.training.controller.command.Command;
import by.training.controller.command.CommandName;
import by.training.controller.command.postcommand.impl.*;
import by.training.controller.command.postcommand.impl.WrongRequestPostCommand;
import by.training.controller.command.postcommand.impl.moder.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Command provider for POST request.
 */
public final class PostCommandProvider {

    private static final PostCommandProvider INSTANCE = new PostCommandProvider();
    /**
     * Map that contains {@link CommandName} and its corresponding implementation of {@link Command} interface.
     */
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private PostCommandProvider() {
        repository.put(CommandName.REGISTRATION, new RegistrationPostCommand());
        repository.put(CommandName.LOGIN, new LoginPostCommand());
        repository.put(CommandName.SAVE_GENRE, new SaveGenrePostCommand());
        repository.put(CommandName.DELETE_GENRE, new DeleteGenrePostCommand());
        repository.put(CommandName.SAVE_SERIAL, new SaveSerialPostCommand());
        repository.put(CommandName.ADD_USER, new AddUserPostCommand());
        repository.put(CommandName.SAVE_COUNTRY, new SaveCountryPostCommand());
        repository.put(CommandName.SAVE_STUDIO, new SaveStudioPostCommand());
        repository.put(CommandName.CHANGE_PASSWORD, new ChangePasswordPostCommand());
        repository.put(CommandName.SAVE_COMMENT, new SaveCommentPostCommand());
        repository.put(CommandName.DELETE_COUNTRY, new DeleteCountryPostCommand());
        repository.put(CommandName.DELETE_STUDIO, new DeleteStudioPostCommand());
        repository.put(CommandName.DELETE_SERIAL, new DeleteSerialPostCommand());
        repository.put(CommandName.DELETE_COMMENT, new DeleteCommentPostCommand());
        repository.put(CommandName.CHANGE_AVATAR, new ChangeUserAvatarPostCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestPostCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PostCommandProvider getInstance() {
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
