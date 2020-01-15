package by.training.abstracttextfile.main;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;
import by.training.abstracttextfile.entity.TextFile;
import by.training.abstracttextfile.view.ConsoleView;

public class Test {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

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

        File file2 = new TextFile(subDirectory1, "FILE.txt");
        File file3 = new File(subDirectory2, "asdasd.TEST");

        TextFile file4 = new TextFile(subDirectory3, "newFile.doc");

        view.viewCatalog(directory);
    }
}
