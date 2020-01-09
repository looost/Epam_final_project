package by.training.exerciseaccount;

import by.training.exerciseaccount.entity.Account;
import by.training.exerciseaccount.entity.Client;

public class ClientCreator {
    public static Client giveClient() {
        Account account1 = new Account(1, 483.00);
        Account account2 = new Account(2, 387.95);
        Account account3 = new Account(3, 232.11);
        Account account4 = new Account(4, 753.23);
        Account account5 = new Account(5, -1281.12);
        Account account6 = new Account(6, 121.76);
        Account account7 = new Account(7, -223.62);
        Account account8 = new Account(8, 246.75);
        Account account9 = new Account(9, 346.33);
        Account account10 = new Account(10, -575.96);


        Client client = new Client(1,"Billy");
        client.setAccount(account1);
        client.setAccount(account2);
        client.setAccount(account3);
        client.setAccount(account4);
        client.setAccount(account5);
        client.setAccount(account6);
        client.setAccount(account7);
        client.setAccount(account8);
        client.setAccount(account9);
        client.setAccount(account10);

        return client;
    }
}
