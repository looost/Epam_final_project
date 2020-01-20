package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.controller.command.CommandResponse;
import by.training.dragon.service.exception.ServiceException;
import by.training.dragon.service.factory.ServiceFactory;

import java.util.ArrayList;

public class TreasureForAGivenAmount implements Command {
    @Override
    public CommandResponse execute(String request) {
        if (request.split(" ").length < 2) {
            return new CommandResponse(new ArrayList<>(), "Не введено значение!");
        }
        try {
            return new CommandResponse(ServiceFactory.getInstance()
                    .getCaveService()
                    .getTreasureGivenAmount(Integer.parseInt(request.split(" ")[1])), "OK");
        } catch (ServiceException e) {
            return new CommandResponse(new ArrayList<>(), e.getMessage());
        } catch (NumberFormatException e) {
            return new CommandResponse(new ArrayList<>(), "Введено не корректное значение!");
        }
    }
}
