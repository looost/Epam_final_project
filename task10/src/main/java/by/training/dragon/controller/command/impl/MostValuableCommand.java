package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class MostValuableCommand implements Command {
    private String response;
    @Override
    public List<Treasure> execute(String request) {
        try {
            response = "OK";
            return ServiceFactory.getInstance().getCaveService().getMostValuableTreasure();
        } catch (ServiceException e) {
            response = "Файл не найден";
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public String getResponse() {
        return response;
    }
}
