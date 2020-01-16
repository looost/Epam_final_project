package by.training.payment.controller;

import by.training.payment.controller.command.Command;
import by.training.payment.controller.command.CommandProvider;
import by.training.payment.entity.Market;
import by.training.payment.service.exeception.ServiceException;
import by.training.payment.service.factory.ServiceFactory;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Command getCommand(String command) {
        return provider.getCommand(command);
    }

    public Market getMarket() {
        try {
            return ServiceFactory.getInstance().getMarketService().getMarket();
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
            return new Market();
        }
    }
}
