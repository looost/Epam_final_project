package by.training.abstracttextfile.view;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;

public class ConsoleView {

    public void viewCatalog(Directory directory) {

        System.out.println(directory.getPath());
        for (Directory d : directory.getSubDirectory()
        ) {
            viewCatalog(d);
        }
        for (File f : directory.getFiles()
        ) {
            System.out.println("  " + f.getName());
        }
    }
}
