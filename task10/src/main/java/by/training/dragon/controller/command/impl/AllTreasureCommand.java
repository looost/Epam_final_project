package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class AllTreasureCommand implements Command {
    private String response;
    @Override
    public List<Treasure> execute(String request) {
        try {
            this.response = "OK";
            return ServiceFactory.getInstance().getCaveService().getAllTreasure();
        } catch (ServiceException e) {
            response = e.getMessage();
            return new ArrayList<>();
        }
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
