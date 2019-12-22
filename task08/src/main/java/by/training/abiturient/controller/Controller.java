package by.training.abiturient.controller;

import by.training.abiturient.service.AbiturientCreator;
import by.training.abiturient.service.AbiturientService;
import by.training.abiturient.entity.BaseOfAbiturient;
import by.training.abiturient.view.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Controller {
    public void execute() throws IOException {
        View view = new View();
        AbiturientService abiturientService = new AbiturientService();
        AbiturientCreator creator = new AbiturientCreator();
        Stream<String> streamFromFiles = Files.lines(Paths.get("src\\main\\resources\\AbiturientList.txt"));
        creator.fillFromStream(BaseOfAbiturient.getInstance(), streamFromFiles);

        int choice = abiturientService.whatShow();
        if (choice == 1) {
            view.showTerminal(abiturientService.abiturientsWithUnsatisfactoryGrades());
            view.writeFile(abiturientService.abiturientsWithUnsatisfactoryGrades());
        } else if (choice == 2) {
            view.showTerminal(abiturientService.abiturientsWithHigherGrade(20));
            view.writeFile(abiturientService.abiturientsWithHigherGrade(20));
        } else {
            System.out.println("Неверное значение");
        }
    }
}
