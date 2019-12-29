package by.training.abiturient.controller;

import by.training.abiturient.view.View;

import java.io.IOException;

public class NewMain {
    Controller controller = new Controller();
    View view = new View();
    private int input;

    public NewMain() throws IOException {
    }


    private void menu() {
        view.showMessage("----------------------------Меню-------------------------------");
        view.showMessage("1 - Полный список абитуриентов в алфавитном порядке");
        view.showMessage("2 - Список абитуриентов, имеющих неудовлетворительные оценки");
        view.showMessage("3 - Список абитуриентов, у которых сумма баллов выше заданной");
        view.showMessage("4 - Выбрать заданное число n абитуриентов, имеющих самую высокую\n" +
                "сумму баллов");
        view.showMessage("5 - Выбрать заданное число n абитуриентов, имеющих самую низкую\n" +
                "сумму баллов");
        view.showMessage("0 - Выход");

    }

    public static void main(String[] args) throws IOException {
        NewMain main = new NewMain();

    }


}
