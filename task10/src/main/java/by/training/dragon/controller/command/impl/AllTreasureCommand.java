package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.controller.command.CommandResponse;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;

public class AllTreasureCommand implements Command {
    @Override
    public CommandResponse execute(String request) {
        try {
            return new CommandResponse(ServiceFactory.getInstance().getCaveService().getAllTreasure(), "OK");
        } catch (ServiceException e) {
            return new CommandResponse(new ArrayList<>(), e.getMessage());
        }
    }
}
