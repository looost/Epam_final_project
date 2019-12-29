package by.training.abiturientV2.View;

import by.training.abiturientV2.controller.Controller;


import java.util.Scanner;

public class Runner {
    private View view = new View();
    private Scanner scanner = new Scanner(System.in);
    private int input;

    private void zeroCheck() {
        view.showMessage("Для возврата введите 0 - ");
        input = checkInput(scanner);
        if (input == 0) {
            execute();
        } else {
            zeroCheck();
        }
    }

    private int checkInput(Scanner s) {
        while (!s.hasNextInt()) {
            view.showMessage("Введите числовое значение - ");
            s.next();
        }
        return s.nextInt();
    }

    private void execute() {
        Controller controller = new Controller();
        view.showMenu();
        input = checkInput(scanner);
        if (input == 0) {
            view.showMessage("До свидания!");
            scanner.close();
            System.exit(0);
        } else if (input == 3) {
            view.showMessage("Введите сумму баллов - ");
        } else if (input == 4 || input == 5) {
            view.showMessage("Введите число абитуриентов - ");
        }
        view.showTerminal(controller.executeTask(input, scanner));
        zeroCheck();
    }


    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.execute();
    }
}
