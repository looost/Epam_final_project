package by.training.exerciseaccount.service;

import by.training.exerciseaccount.entity.Account;
import by.training.exerciseaccount.entity.Client;

public class ClientService {

    public double totalAmount(Client client) {
        double sum = 0;
        for (Account acc:client.getAccountList()
             ) {
            sum+=acc.getBalance();
        }
        return sum;
    }

    public double totalAmountWithPositiveBalance(Client client) {
        double sum = 0;
        for (Account acc:client.getAccountList()
        ) {
            if (acc.getBalance() >= 0) {
                sum += acc.getBalance();
            }
        }
        return sum;
    }

    public double totalAmountWithNegativeBalance(Client client) {
        double sum = 0;
        for (Account acc:client.getAccountList()
        ) {
            if (acc.getBalance() < 0) {
                sum += acc.getBalance();
            }
        }
        return sum;
    }


}
