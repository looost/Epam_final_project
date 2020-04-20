package by.training.controller.command.postcommand;

import by.training.controller.command.Command;
import by.training.controller.command.CommandName;
import by.training.controller.command.postcommand.impl.*;
import by.training.controller.command.postcommand.impl.WrongRequestPostCommand;
import by.training.controller.command.postcommand.impl.moder.*;

import java.util.EnumMap;
import java.util.Map;

public class PostCommandProvider {
    private static final PostCommandProvider instance = new PostCommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private PostCommandProvider() {
        repository.put(CommandName.REGISTRATION, new RegistrPostCommand());
        repository.put(CommandName.LOGIN, new LoginPostCommand());
        repository.put(CommandName.SAVE_GENRE, new SaveGenrePostCommand());
        repository.put(CommandName.DELETE_GENRE, new DeleteGenrePostCommand());
        repository.put(CommandName.ADD_SERIAL, new AddSerialPostCommand());
        repository.put(CommandName.ADD_USER, new AddUserPostCommand());
        repository.put(CommandName.SAVE_COUNTRY, new SaveCountryPostCommand());
        repository.put(CommandName.SAVE_STUDIO, new SaveStudioPostCommand());
        repository.put(CommandName.CHANGE_SERIAL, new ChangeSerialPostCommand());
        repository.put(CommandName.CHANGE_PASSWORD, new ChangePasswordPostCommand());
        repository.put(CommandName.SAVE_COMMENT, new SaveCommentPostCommand());
        repository.put(CommandName.DELETE_COUNTRY, new DeleteCountryPostCommand());
        repository.put(CommandName.DELETE_STUDIO, new DeleteStudioPostCommand());
        repository.put(CommandName.DELETE_SERIAL, new DeleteSerialPostCommand());
        repository.put(CommandName.DELETE_COMMENT, new DeleteCommentPostCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestPostCommand());
    }

    public static PostCommandProvider getInstance() {
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
