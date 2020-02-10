package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.composite.Component;
import by.training.service.factory.ServiceFactory;

public class SortWordInSentenceByLengthCommand implements Command {
    @Override
    public CommandResponse execute(Component component, String request) {
        return new CommandResponse(ServiceFactory.getInstance().getService().sortWordInSentenceByLength(component), "OK");
    }
}
