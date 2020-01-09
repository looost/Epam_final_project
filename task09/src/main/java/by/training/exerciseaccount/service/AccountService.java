package by.training.exerciseaccount.service;

import by.training.exerciseaccount.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {

    public boolean isBlocked(Account account) {
        return account.isBlocked();
    }

    public List <Account> sortByBalance(List <Account> accountList) {
        Account accountTmp;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < accountList.size() - 1; i++) {
                if (accountList.get(i).getBalance() > accountList.get(i + 1).getBalance()) {
                    accountTmp = accountList.get(i);
                    accountList.set(i, accountList.get(i + 1));
                    accountList.set(i + 1, accountTmp);
                    flag = true;
                }
            }
        }
        return accountList;
    }

    public List <Account> findByMinBalance(List <Account> accountList, double minBalance) {
//        List <Account> result = new ArrayList<>();
//        for (Account account:accountList
//        ) {
//            if (minBalance < account.getBalance()) {
//                result.add(account);
//            }
//        }
//        return result;
          return accountList.stream().filter(account -> account.getBalance()>minBalance).collect(Collectors.toList());
    }


}
