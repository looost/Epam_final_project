package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class TreasureForAGivenAmount implements Command {
    private String response;
    @Override
    public List<Treasure> execute(String request) {
        if (request.split(" ").length < 2) {
            response = "Не введено значение!";
            return new ArrayList<>();
        }
        try {
            response = "OK";
            return ServiceFactory.getInstance()
                    .getCaveService().getTreasureGivenAmount(Integer.parseInt(request.split(" ")[1]));
        } catch (ServiceException e) {
            response = e.getMessage();
            return new ArrayList<>();
        } catch (NumberFormatException e) {
            response = "Введено не корректное значение!";
            return new ArrayList<>();
        }
    }

    @Override
    public String getResponse() {
        return response;
    }
}
