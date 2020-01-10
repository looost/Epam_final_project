package by.training.textfile.view;

import by.training.textfile.entity.Directory;
import by.training.textfile.entity.File;

public class ViewConsoleDirectory {

    public void showCatalog(Directory directory) {
        System.out.println(directory.getName());

        for (Directory d : directory.getSubDirectory()
        ) {
            System.out.println("    " + d.getName());
            for (File f : d.getFiles()
            ) {
                System.out.println("        " + f.getName());
            }
        }
    }
}
