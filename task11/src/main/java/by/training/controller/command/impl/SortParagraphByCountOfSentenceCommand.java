package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.entity.composite.Component;
import by.training.service.factory.ServiceFactory;

public class SortParagraphByCountOfSentenceCommand implements Command {
    @Override
    public Component execute(Component component, String request) {
        return ServiceFactory.getInstance().getService().sortParagraphByCountOfSentence(component);
    }
}
