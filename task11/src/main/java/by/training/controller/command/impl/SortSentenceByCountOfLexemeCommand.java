package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.composite.Component;
import by.training.service.factory.ServiceFactory;

public class SortSentenceByCountOfLexemeCommand implements Command {
    @Override
    public CommandResponse execute(Component component) {
        return new CommandResponse(ServiceFactory.getInstance().getService().sortSentenceByCountOfLexeme(component), "OK");
    }
}
