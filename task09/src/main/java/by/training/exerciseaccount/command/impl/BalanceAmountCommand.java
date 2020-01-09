package by.training.exerciseaccount.command.impl;

import by.training.exerciseaccount.ClientCreator;
import by.training.exerciseaccount.command.Command;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.ClientService;
import by.training.exerciseaccount.view.View;

public class BalanceAmountCommand implements Command {
    @Override
    public void execute(String command) {
        View view = new View();
        Client client = ClientCreator.giveClient();
        ClientService clientService = new ClientService();
        view.showMessage("Общая сумма по счетам равна " + clientService.totalAmount(client));
    }
}
