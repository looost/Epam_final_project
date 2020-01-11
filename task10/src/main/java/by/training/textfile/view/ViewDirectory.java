package by.training.textfile.view;

import by.training.textfile.entity.Directory;
import by.training.textfile.entity.File;

public class ViewDirectory {

    public void viewCatalog(Directory directory) {

        System.out.println(directory.getPath());
        for (File f : directory.getFiles()
        ) {
            System.out.println("  " + f.getName());
        }

        for (Directory d : directory.getSubDirectory()
        ) {
            System.out.println("  " + d.getPath());

            for (File f : d.getFiles()
            ) {
                System.out.println("      " + f.getName());
            }

        }
    }

}
