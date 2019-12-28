package by.training.abiturient.controller;

import by.training.abiturient.dao.ReadFile;
import by.training.abiturient.dao.WriteFile;
import by.training.abiturient.entity.BaseOfAbiturient;
import by.training.abiturient.service.AbiturientCreator;
import by.training.abiturient.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
    private static Scanner scan = new Scanner(System.in);
    private int input;
    private View view = new View();
    private WriteFile writeFile = new WriteFile();
    private BaseOfAbiturient baseOfAbiturient = BaseOfAbiturient.getInstance();

    Controller() throws IOException {                //!!!!!!!!!!!!!!!!!!!!
        ReadFile readFile = new ReadFile();
        AbiturientCreator creator = new AbiturientCreator();
        creator.fillFromStream(baseOfAbiturient, readFile.readFromFile("src\\main\\resources\\AbiturientList.txt"));
    }

    public void execute() throws IOException {
        menu();
        input = checkInput(scan);
        if (input == 0) {
            view.showMessage("До свидания!");
            scan.close();
        } else {
            subMenu(input);
        }
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

    private void subMenu(int choice) throws IOException {
        String message = "Для возврата нажмите 0";
        switch (choice) {
            case 1:
                listOfAbiturient();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 2:
                badGrades();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 3:
                sumGrade();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 4:
                sortBest();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 5:
                sortWorst();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            default:
                System.out.println("Неправильный выбор!");
                execute();
        }
    }

    private void listOfAbiturient() {
        view.showTerminal(baseOfAbiturient.getListOfAbiturient().sortByLastName());
        System.out.println();
    }

    private void sumGrade() throws FileNotFoundException {
        view.showMessage("Введите кол-во баллов - ");
        input = checkInput(scan);
        view.showTerminal(baseOfAbiturient.getListOfAbiturient().abiturientsWithHigherGrade(input));
        writeFile.writeToFile("src\\main\\resources\\result.txt", baseOfAbiturient.getListOfAbiturient().abiturientsWithHigherGrade(input));
        System.out.println();
    }

    private void badGrades() throws FileNotFoundException {
        view.showTerminal(baseOfAbiturient.getListOfAbiturient().abiturientsWithUnsatisfactoryGrades());
        writeFile.writeToFile("src\\main\\resources\\result.txt", baseOfAbiturient.getListOfAbiturient().abiturientsWithUnsatisfactoryGrades());
        System.out.println();
    }

    private void sortWorst() {
        view.showMessage("Введите число студентов - ");
        input = checkInput(scan);
        if (input > baseOfAbiturient.getListOfAbiturient().getAbiturientList().size()) {
            input = baseOfAbiturient.getListOfAbiturient().getAbiturientList().size();
        }
        view.showTerminal(baseOfAbiturient.getListOfAbiturient().sortBySumGradeWorst(input));
        System.out.println();
    }


    private void sortBest() {
        view.showMessage("Введите число студентов - ");
        input = checkInput(scan);
        if (input > baseOfAbiturient.getListOfAbiturient().getAbiturientList().size()) {
            input = baseOfAbiturient.getListOfAbiturient().getAbiturientList().size();
        }
        view.showTerminal(baseOfAbiturient.getListOfAbiturient().sortBySumGradeBest(input));
        System.out.println();
    }


    private void zeroCheck(Integer input) throws IOException {
        if (input == 0) {
            execute();
        }
    }

    private Integer checkInput(Scanner s) {
        while (!s.hasNextInt()) {
            view.showMessage("Введите числовое значение - ");
            s.next();
        }
        return s.nextInt();
    }
}