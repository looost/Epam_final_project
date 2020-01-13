package by.training.textfile.main;


import by.training.textfile.creator.Creator;
import by.training.textfile.entity.Directory;
import by.training.textfile.entity.TextFile;
import by.training.textfile.exception.FileException;
import by.training.textfile.service.TextFileService;
import by.training.textfile.view.ViewDirectory;
import by.training.textfile.view.ViewFile;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, FileException {
        ViewFile viewFile = new ViewFile();
        ViewDirectory viewDirectory = new ViewDirectory();
        TextFileService textFileService = new TextFileService();

        Directory directory = Creator.createDirectory();

        //TextFile textFile1 = new TextFile(directory, "renameFile.txt");
        //textFile1.createNewFile();
        //Creator.writeFile(textFile1);
        viewDirectory.viewCatalog(directory);
        //viewFile.viewData(textFileService.getData(textFile1));

        //System.out.println(textFile1.delete());
    }
}
