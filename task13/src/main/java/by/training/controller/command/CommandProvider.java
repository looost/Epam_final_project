package by.training.controller.command;

import by.training.controller.command.impl.DOMParserCommand;
import by.training.controller.command.impl.SAXParserCommand;
import by.training.controller.command.impl.StAXParserCommand;
import by.training.controller.command.impl.WrongRequestCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {

    private Logger logger = LogManager.getLogger("logger");
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.SAX, new SAXParserCommand());
        repository.put(CommandName.STAX, new StAXParserCommand());
        repository.put(CommandName.DOM, new DOMParserCommand());
        repository.put(CommandName.WRONG_REQEST, new WrongRequestCommand());
    }

    public Command getCommand(String request) {
        try {
            return repository.get(CommandName.valueOf(request.toUpperCase()));
        } catch (IllegalArgumentException e) {
            logger.error("Введена неверная команда - " + request.toUpperCase());
            return repository.get(CommandName.WRONG_REQEST);
        }
    }
}
