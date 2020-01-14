package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class AllTreasureCommand implements Command {
    @Override
    public List<Treasure> execute(String request) {
        try {
            return ServiceFactory.getInstance().getCaveService().getAllTreasure();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
