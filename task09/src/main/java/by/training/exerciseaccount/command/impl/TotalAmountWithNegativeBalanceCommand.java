package by.training.exerciseaccount.command.impl;

import by.training.exerciseaccount.ClientCreator;
import by.training.exerciseaccount.command.Command;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.ClientService;
import by.training.exerciseaccount.view.View;

public class TotalAmountWithNegativeBalanceCommand implements Command {
    @Override
    public void execute(String command) {
        View view = new View();
        Client client = ClientCreator.giveClient();
        ClientService clientService = new ClientService();
        view.showMessage("Сумма по всем счетам, имеющим отрицательный баланс равна " + clientService.totalAmountWithNegativeBalance(client));
    }
}
