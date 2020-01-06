package by.training.exerciseaccount;

import by.training.exerciseaccount.entity.Account;
import by.training.exerciseaccount.entity.Bank;
import by.training.exerciseaccount.entity.Client;
import by.training.exerciseaccount.service.AccountService;
import by.training.exerciseaccount.service.ClientService;


public class Test {
    public static void main(String[] args) {
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

        Bank bank = new Bank("New Bank");
        bank.setClients(client);

        ClientService clientService = new ClientService();

        System.out.println(clientService.totalAmount(client));
        System.out.println(clientService.totalAmountWithPositiveBalance(client));
        System.out.println(clientService.totalAmountWithNegativeBalance(client));

        System.out.println("-------------");
        for (Account acc:client.getAccountList()
             ) {
            System.out.println(acc.getBalance());
        }

        AccountService accountService = new AccountService();
        accountService.sortByBalance(client);

        System.out.println("-------------");
        for (Account acc:client.getAccountList()
        ) {
            System.out.println(acc.getBalance());
        }

        System.out.println("-------------");
        System.out.println("-------------");

        for (Account acc:accountService.findByMinBalance(client, -8400)
             ) {
            System.out.println(acc);
        }
    }
}
