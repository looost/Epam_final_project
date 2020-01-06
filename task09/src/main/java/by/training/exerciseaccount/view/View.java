package by.training.exerciseaccount.view;

import by.training.exerciseaccount.entity.Account;

import java.util.List;

public class View {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTotalSum(double sum) {
        System.out.println("Общая сумма по счетам равна " + sum );
    }

    public void showTotalSumWithPositiveBalance(double sum) {
        System.out.println("Сумма по всем счетам, имеющим положительный баланс равна " + sum);
    }

    public void showTotalSumWithNegativeBalance(double sum) {
        System.out.println("Сумма по всем счетам, имеющим отрицательный баланс равна " + sum);
    }

    public void showBalanceAccount(List <Account> account){
        int count = 0;
        for (Account acc:account
             ) {
            System.out.println("Аккаунт номер" + count++ + " имеет баланс - " + acc.getBalance());
        }
    }

}
