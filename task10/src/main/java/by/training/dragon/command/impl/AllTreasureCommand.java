package by.training.dragon.command.impl;

import by.training.dragon.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.List;

public class AllTreasureCommand implements Command {
    @Override
    public List<Treasure> execute(String request) {
        try {
            return ServiceFactory.getInstance().getCaveService().getAllTreasure();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
