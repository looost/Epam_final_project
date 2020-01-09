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
        view.showAccountBalance(client.getAccountList());
    }

    public void executeBalanceAmount(Client client) {
        view.showMessage("Общая сумма по счетам равна " + clientService.totalAmount(client));
    }

    public void executeTotalAmountWithPositiveBalance(Client client) {
        view.showMessage("Сумма по всем счетам, имеющим положительный баланс равна " + clientService.totalAmountWithPositiveBalance(client));
    }

    public void executeTotalAmountWithNegativeBalance(Client client) {
        view.showMessage("Сумма по всем счетам, имеющим отрицательный баланс равна " + clientService.totalAmountWithNegativeBalance(client));
    }

    public void executeFindByMinBalance(Client client, double minAmount) {
        view.showAccountBalance(accountService.findByMinBalance(client.getAccountList(), minAmount));
    }

    public void executeSortByBalance(Client client) {
        view.showAccountBalance(accountService.sortByBalance(client.getAccountList()));
    }
}
