package by.training.dragon.command.impl;

import by.training.dragon.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class MostValuableCommand implements Command {
    @Override
    public List<Treasure> execute(String request) {
        try {
            List<Treasure> list = new ArrayList<>();
            list.add(ServiceFactory.getInstance().getCaveService().getMostValuableTreasure());
            return list;
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
