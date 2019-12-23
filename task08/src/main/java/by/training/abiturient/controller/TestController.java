package by.training.abiturient.controller;

import by.training.abiturient.entity.BaseOfAbiturient;
import by.training.abiturient.service.AbiturientCreator;
import by.training.abiturient.service.AbiturientService;
import by.training.abiturient.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestController {
    private static Scanner scan = new Scanner(System.in);
    private static int input;
    private View view = new View();
    private AbiturientService abiturientService = new AbiturientService();

    TestController() throws IOException {
        AbiturientCreator creator = new AbiturientCreator();
        Stream<String> streamFromFiles = Files.lines(Paths.get("src\\main\\resources\\AbiturientList.txt"));
        creator.fillFromStream(BaseOfAbiturient.getInstance(), streamFromFiles);
    }

    public void execute() throws IOException {
        menu();
        input = checkInput(scan);
        if (input < 5 && input > 0)
            subMenu(input);
        else if (input == 0) {
            view.showMessage("До свидания!");
            scan.close();
            System.exit(0);
        } else {
            view.showMessage("Не верное значение!");
            execute();
        }
    }

    private void menu() {
        view.showMessage("----------------------------Меню-------------------------------");
        view.showMessage("1 - Список абитуриентов, имеющих неудовлетворительные оценки");
        view.showMessage("2 - Список абитуриентов, у которых сумма баллов выше заданной");
        view.showMessage("3 - Выбрать заданное число n абитуриентов, имеющих самую высокую\n" +
                "сумму баллов");
        view.showMessage("4 - Выбрать заданное число n абитуриентов, имеющих самую низкую\n" +
                "сумму баллов");
        view.showMessage("0 - Выход");

    }

    private void subMenu(Integer choice) throws IOException {
        String message = "Для возврата нажмите 0";
        switch (choice) {
            case 1:
                badGrades();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 2:
                sumGrade();
                view.showMessage(message);
                input = checkInput(scan);
                zeroCheck(input);
                break;
            case 3:
                sortWorst();
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
            default:
                System.out.println("Неправильный выбор!");
                menu();
        }
    }

    private void sumGrade() throws FileNotFoundException {
        view.showMessage("Введите кол-во баллов - ");
        input = checkInput(scan);
        view.showTerminal(abiturientService.abiturientsWithHigherGrade(input));
        view.writeInFile(abiturientService.abiturientsWithHigherGrade(input));
        System.out.println();
    }

    private void badGrades() throws FileNotFoundException {
        view.showTerminal(abiturientService.abiturientsWithUnsatisfactoryGrades());
        view.writeInFile(abiturientService.abiturientsWithUnsatisfactoryGrades());
        System.out.println();
    }

    private void sortWorst() {
        abiturientService.sortBySumGradeWorst();
        view.showMessage("Введите число студентов - ");
        input = checkInput(scan);
        for (int i = 0; i < input; i++) {
            System.out.println(BaseOfAbiturient.getInstance()
                    .getBaseOfAbiturient()
                    .get(i));
        }
        System.out.println();
    }

    private void sortBest() {
        abiturientService.sortBySumGradeBest();
        view.showMessage("Введите число студентов - ");
        input = checkInput(scan);
        for (int i = 0; i < input; i++) {
            System.out.println(BaseOfAbiturient.getInstance()
                    .getBaseOfAbiturient()
                    .get(i));
        }
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