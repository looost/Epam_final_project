package by.training.abstracttextfile.view;

import by.training.abstracttextfile.controller.Controller;
import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;
import by.training.abstracttextfile.entity.TextFile;
import by.training.abstracttextfile.view.ConsoleView;

import java.util.Scanner;

public class UserUI {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        Directory main = new Directory("main");
        String request;
        String response;
        boolean flag = true;

        while (flag) {
            view.showMenu();
            request = scanner.nextLine();
            response = controller.getCommand(request).execute(main, request);
            if (response.equalsIgnoreCase("OK")) {
                view.viewCatalog(main);
            } else {
                view.showError(response);
            }
            view.showMessage("\nВведите что угодно, что бы продолжить, exit - для выхода");
            request = scanner.nextLine();
            if (request.equalsIgnoreCase("exit")) {
                flag = false;
            }
        }
    /*
        Directory directory = new Directory("main");
        Directory subDirectory1 = new Directory("sub1");
        directory.addSubDirectory(subDirectory1);
        Directory subDirectory2 = new Directory("sub2");
        directory.addSubDirectory(subDirectory2);
        Directory subDirectory3 = new Directory("sub3");
        subDirectory2.addSubDirectory(subDirectory3);
        Directory subDirectory4 = new Directory("sub4");
        directory.addSubDirectory(subDirectory4);

        File file = new TextFile(directory, "FILE.txt");
        File file1 = new File(directory, "asdasd.TEST");
        file.rename("ff");
        System.out.println(file.getNameWithDirectory());
        File file2 = new TextFile(subDirectory1, "FILE.txt");
        File file3 = new File(subDirectory2, "asdasd.TEST");

        TextFile file4 = new TextFile(subDirectory3, "newFile.doc");

        view.viewCatalog(directory);
    */
    }
}
