package by.training.exerciseaccount.view;

import by.training.exerciseaccount.entity.Account;

import java.util.List;

public class View {

    public void showMenu() {
        System.out.println("-------------------------Меню-------------------------");
        System.out.println("1 - Вывести баланс всех счетов");
        System.out.println("2 - Вывести общий баланс всех счетов");
        System.out.println("3 - Вывести сумму по всем счетам, имеющим положительный баланс");
        System.out.println("4 - Вывести сумму по всем счетам, имеющим отрицательный баланс");
        System.out.println("5 - Выполнить поиск счета");
        System.out.println("6 - Выполнить сортировку счетов по балансу");
        System.out.println("0 - Выход");
    }

    public void subFindMenu() {
        System.out.println("1 - Поиск счетов, баланс которых выше заданого");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showAccountBalance(List <Account> account){
        for (Account acc:account
             ) {
            System.out.println("Аккаунт номер " + acc.getAccountId() + " имеет баланс - " + acc.getBalance());
        }
    }

}
