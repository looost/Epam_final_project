package by.training.exerciseaccount.main;

import by.training.exerciseaccount.entity.Account;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.AccountService;
import by.training.exerciseaccount.service.ClientService;
import by.training.exerciseaccount.view.View;

public class Runner {
    public static void main(String[] args) {
        View view = new View();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();

        Account account1 = new Account(1, 483.00);
        Account account2 = new Account(1, 387.95);
        Account account3 = new Account(2, 232.11);
        Account account4 = new Account(1, 753.23);
        Account account5 = new Account(1, -1281.12);

        Client client = new Client(1,"Billy");
        client.setAccount(account1);
        client.setAccount(account2);
        client.setAccount(account3);
        client.setAccount(account4);
        client.setAccount(account5);



    }
}
