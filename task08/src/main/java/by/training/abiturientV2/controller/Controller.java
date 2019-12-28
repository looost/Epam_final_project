package by.training.abiturientV2.controller;

import by.training.abiturientV2.service.AbiturientService;
import by.training.abiturientV2.service.ServiceFactory;
import by.training.abiturientV2.entity.Abiturient;

import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AbiturientService abiturientService = serviceFactory.getAbiturientService();


    public Stream<Abiturient> executeTask(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                return abiturientService.sortByLastName();
            case 2:
                return abiturientService.abiturientsWithUnsatisfactoryGrades();
            case 3:
                return abiturientService.abiturientsWithHigherGrade(checkInput(scanner));
            case 4:
                return abiturientService.sortBySumGradeBest(checkInput(scanner));
            case 5:
                return abiturientService.sortBySumGradeWorst(checkInput(scanner));
            default:
                return abiturientService.sortByLastName();
        }
    }

    private int checkInput(Scanner s) {
        while (!s.hasNextInt()) {
            System.out.println("Введите числовое значение - ");
            s.next();
        }
        return s.nextInt();
    }
}
