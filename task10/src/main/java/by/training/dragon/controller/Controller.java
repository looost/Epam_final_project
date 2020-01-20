package by.training.dragon.controller;

import by.training.dragon.controller.command.Command;
import by.training.dragon.controller.command.CommandProvider;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.List;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Command getCommand(String request) {
        return provider.getCommand(request);
    }

    public int totalAmount(List<Treasure> treasureList) {
        return ServiceFactory.getInstance().getCaveService().totalAmount(treasureList);
    }
}
