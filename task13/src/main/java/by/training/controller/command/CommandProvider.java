package by.training.controller.command;

import by.training.controller.command.impl.DOMParserCommand;
import by.training.controller.command.impl.SAXParserCommand;
import by.training.controller.command.impl.StAXParserCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.SAX, new SAXParserCommand());
        repository.put(CommandName.STAX, new StAXParserCommand());
        repository.put(CommandName.DOM, new DOMParserCommand());
    }

    public Command getCommand(String request) {
        return repository.get(CommandName.valueOf(request.toUpperCase()));
    }
}
