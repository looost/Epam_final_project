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
        repository.put(CommandName.REGISTR, new RegistrPostCommand());
        repository.put(CommandName.LOGINPOST, new LoginPostCommand());
        repository.put(CommandName.ADD_COMMENT, new AddCommentPostCommand());
        repository.put(CommandName.ADD_GENRE, new AddGenrePostCommand());
        repository.put(CommandName.CHANGE_GENRE, new ChangeGenrePostCommand());
        repository.put(CommandName.DELETE_GENRE, new DeleteGenrePostCommand());
        repository.put(CommandName.ADD_COUNTRY, new AddCountryPostCommand());
        repository.put(CommandName.ADD_STUDIO, new AddStudioPostCommand());
        repository.put(CommandName.ADD_SERIAL, new AddSerialPostCommand());
        repository.put(CommandName.CHANGE_COUNTRY, new ChangeCountryPostCommand());
        repository.put(CommandName.CHANGE_STUDIO, new ChangeStudioPostCommand());
        repository.put(CommandName.CHANGE_SERIAL, new ChangeSerialPostCommand());
        repository.put(CommandName.DELETE_COUNTRY, new DeleteCountryPostCommand());
        repository.put(CommandName.DELETE_STUDIO, new DeleteStudioPostCommand());
        repository.put(CommandName.DELETE_SERIAL, new DeleteSerialPostCommand());
        repository.put(CommandName.PROFILEPOST, new ProfilePostCommand());
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
