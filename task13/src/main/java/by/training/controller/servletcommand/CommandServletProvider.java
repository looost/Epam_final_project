package by.training.controller.servletcommand;

import by.training.controller.servletcommand.impl.ParserGetCommandServlet;
import by.training.controller.servletcommand.impl.XmlCommandServlet;
import by.training.controller.servletcommand.impl.LanguageCommandServlet;
import by.training.controller.servletcommand.impl.ParserCommandServlet;

import java.util.EnumMap;
import java.util.Map;

public class CommandServletProvider {
    private final Map<CommandServletName, CommandServlet> repository = new EnumMap<>(CommandServletName.class);

    public CommandServletProvider() {
        repository.put(CommandServletName.XML, new XmlCommandServlet());
        repository.put(CommandServletName.PARSER, new ParserCommandServlet());
        repository.put(CommandServletName.LANGUAGE, new LanguageCommandServlet());
        repository.put(CommandServletName.PARSERGET, new ParserGetCommandServlet());
    }

    public CommandServlet getCommand(String commandServletName) {
        return repository.get(CommandServletName.valueOf(commandServletName.toUpperCase()));
    }
}
