package by.training.exerciseaccount.service;

import by.training.exerciseaccount.entity.Account;
import by.training.exerciseaccount.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AccountService {

    public boolean isBlocked(Account account) {
        return account.isBlocked();
    }

    public List <Account> sortByBalance(Client client) {
        Account accountTmp;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < client.getAccountList().size() - 1; i++) {
                if (client.getAccountList().get(i).getBalance() > client.getAccountList().get(i + 1).getBalance()) {
                    accountTmp = client.getAccountList().get(i);
                    client.getAccountList().set(i, client.getAccountList().get(i + 1));
                    client.getAccountList().set(i + 1, accountTmp);
                    flag = true;
                }
            }
        }
        return client.getAccountList();
    }

    public List <Account> findByMinBalance(Client client, double minBalance) {
        List <Account> result = new ArrayList<>();
        for (Account account:client.getAccountList()
             ) {
            if (minBalance < account.getBalance()) {
                result.add(account);
            }
        }
        return result;
    }
}
