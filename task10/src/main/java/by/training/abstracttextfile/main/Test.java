package by.training.abstracttextfile.main;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;
import by.training.abstracttextfile.entity.TextFile;

public class Test {
    public static void main(String[] args) {
        Directory directory = new Directory("C:\\main");
        File file = new TextFile(directory, "FILE.txt");
        File file1 = new File(directory, "asdasd.TEST");
        System.out.println(file.getName());
        file.rename("newName");
        System.out.println(file.getNameWithDirectory());
        System.out.println(file1.getName());
        file1.rename("Opaaa");
        System.out.println(file1.getNameWithDirectory());
    }
}
