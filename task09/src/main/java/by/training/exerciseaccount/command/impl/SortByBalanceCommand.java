package by.training.exerciseaccount.command.impl;

import by.training.exerciseaccount.ClientCreator;
import by.training.exerciseaccount.command.Command;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.AccountService;
import by.training.exerciseaccount.view.View;

public class SortByBalanceCommand implements Command {
    @Override
    public void execute(String command) {
        View view = new View();
        Client client = ClientCreator.giveClient();
        AccountService accountService = new AccountService();
        view.showAccountBalance(accountService.sortByBalance(client.getAccountList()));
    }
}
