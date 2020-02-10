package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortLexemeInSentenceByCharCommand implements Command {
    private static final Logger logger = LogManager.getLogger("wrongRequest");

    @Override
    public CommandResponse execute(Component component, String request) {
        String[] requestArray = request.split(" ");
        if (requestArray.length < 2) {
            logger.error("Не введен символ!");
            return new CommandResponse(new Composite(Type.WORD), "Не введен символ!");
        }
        return new CommandResponse(ServiceFactory.getInstance().getService().sortLexemeInSentenceByChar(component, requestArray[1].charAt(0)), "OK");
    }
}
