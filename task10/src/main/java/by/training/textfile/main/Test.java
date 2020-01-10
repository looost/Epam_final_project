package by.training.textfile.main;


import by.training.textfile.FileException;
import by.training.textfile.entity.Directory;
import by.training.textfile.entity.File;
import by.training.textfile.entity.TextFile;
import by.training.textfile.service.TextFileService;
import by.training.textfile.view.ViewConsoleDirectory;
import by.training.textfile.view.ViewConsoleTextFile;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {


        TextFileService fileService = new TextFileService();
        ViewConsoleTextFile viewFile = new ViewConsoleTextFile();
        ViewConsoleDirectory viewDirectory = new ViewConsoleDirectory();

        try {
            Directory mainDirectory = new Directory("C:\\TestProject");
            List<File> list1 = new ArrayList<>();
            list1.add(fileService.createByDefault(mainDirectory));
            list1.add(new TextFile("file1", "pdf", mainDirectory));
            list1.add(new TextFile("file2", "fb2", mainDirectory));
            list1.add(new TextFile("file3", "doc", mainDirectory));
            Directory subDirectory = new Directory("C:\\TestProject");
            System.out.println("---------");


            //Directory subDirectory1 = new Directory();


        } catch (FileException e) {
            System.out.println(e.getMessage());
        }


    }
}
