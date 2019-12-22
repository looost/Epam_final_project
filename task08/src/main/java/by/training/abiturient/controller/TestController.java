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
    private static Integer input;
    View view = new View();
    AbiturientService abiturientService = new AbiturientService();

    TestController() throws IOException {
        AbiturientCreator creator = new AbiturientCreator();
        Stream<String> streamFromFiles = Files.lines(Paths.get("src\\main\\resources\\AbiturientList.txt"));
        creator.fillFromStream(BaseOfAbiturient.getInstance(), streamFromFiles);
    }

    public void execute() throws IOException {
        Menu();
        input = checkInput(scan);
        if(input < 4 && input > 0)
            SubMenu(input);
        else if (input == 0){
            System.out.println("До свидания!");
            System.exit(0);
        } else{
            System.out.println("Не верное значение!");
            execute();
        }
    }

    private void Menu() {
        System.out.println("----------------------------Меню-------------------------------");
        System.out.println("1 - Список абитуриентов, имеющих неудовлетворительные оценки");
        System.out.println("2 - Список абитуриентов, у которых сумма баллов выше заданной");
        System.out.println("3 - Выбрать заданное число n абитуриентов, имеющих самую высокую\n" +
                "сумму баллов");
        System.out.println("0 - Выход");

    }

    private void SubMenu(Integer subMenu) throws IOException {
        switch (subMenu) {
            case 1:
                BadMarks();
                System.out.println("Для возврата нажмите 0");
                input = checkInput(scan);
                ZeroCheck(input);
                break;
            case 2:
                markSum();
                System.out.println("Для возврата нажмите 0");
                input = checkInput(scan);
                ZeroCheck(input);
                break;
            case 3:
                sortTest();
                System.out.println("Для возврата нажмите 0");
                input = checkInput(scan);
                ZeroCheck(input);
                break;
            default:
                System.out.println("Неправильный выбор!");
                Menu();
        }
    }

    private void markSum() throws FileNotFoundException {
        System.out.println("Введите кол-во баллов - ");
        Integer input = checkInput(scan);
        view.showTerminal(abiturientService.abiturientsWithHigherGrade(input));
        view.writeFile(abiturientService.abiturientsWithHigherGrade(input));
        System.out.println();
    }

    private void BadMarks() throws FileNotFoundException {
        view.showTerminal(abiturientService.abiturientsWithUnsatisfactoryGrades());
        view.writeFile(abiturientService.abiturientsWithUnsatisfactoryGrades());
        System.out.println();
    }

    private void sortTest() {
        abiturientService.sortBySumGrade();
        System.out.println("Введите число студентов - ");
        Integer input = checkInput(scan);
        for (int i = 0; i < input; i++) {
            System.out.println(BaseOfAbiturient.getInstance()
                    .getBaseOfAbiturient()
                    .get(i));
        }
        System.out.println();
    }

    private void ZeroCheck(Integer input) throws IOException {
        if (input == 0) {
            execute();
        }
    }

    private Integer checkInput(Scanner s) {
        while (!s.hasNextInt()) {
            System.out.print("Введите числовое значение - ");
            s.next();
        }
        return s.nextInt();
    }
}