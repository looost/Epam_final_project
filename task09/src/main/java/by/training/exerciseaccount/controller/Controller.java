package by.training.exerciseaccount.controller;

import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.AccountService;
import by.training.exerciseaccount.service.ClientService;
import by.training.exerciseaccount.view.View;

public class Controller {
    private View view = new View();
    private ClientService clientService = new ClientService();
    private AccountService accountService = new AccountService();

    public void executeInfoBalanceAllAccount(Client client) {
        view.showBalanceAccount(client.getAccountList());
    }

    public void executeBalanceAmount(Client client) {
        view.showTotalSum(clientService.totalAmount(client));
    }

    public void executeTotalAmountWithPositiveBalance(Client client) {
        view.showTotalSumWithPositiveBalance(clientService.totalAmountWithPositiveBalance(client));
    }

    public void executeTotalAmountWithNegativeBalance(Client client) {
        view.showTotalSumWithNegativeBalance(clientService.totalAmountWithNegativeBalance(client));
    }

    public void executeFindByMinAmount(Client client, double minAmount) {
        view.showBalanceAccount(accountService.findByMinBalance(client.getAccountList(), minAmount));
    }

    public void executeSortByBalance(Client client) {
        view.showBalanceAccount(accountService.sortByBalance(client.getAccountList()));
    }
}
