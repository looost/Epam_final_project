package by.training.controller.command;

import by.training.controller.command.impl.ParserGetCommand;
import by.training.controller.command.impl.XmlCommand;
import by.training.controller.command.impl.LanguageCommand;
import by.training.controller.command.impl.ParserCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandServletProvider {
    private final Map<CommandURLName, CommandServlet> repository = new EnumMap<>(CommandURLName.class);

    public CommandServletProvider() {
        repository.put(CommandURLName.XML, new XmlCommand());
        repository.put(CommandURLName.PARSER, new ParserCommand());
        repository.put(CommandURLName.LANGUAGE, new LanguageCommand());
        repository.put(CommandURLName.PARSERGET, new ParserGetCommand());
    }

    public CommandServlet getCommand(String commandServletName) {
        return repository.get(CommandURLName.valueOf(commandServletName.toUpperCase()));
    }
}
