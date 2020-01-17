package by.training.abstracttextfile.view;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;

class ConsoleView {

    void showMenu() {
        System.out.println("Введите, что бы создать: ");
        System.out.println("---> dir <имя директории>");
        System.out.println("---> file <имя файла.расширение>");
        System.out.println("---> text_file <имя файла.расширение>");
        System.out.println("P.S доступные расширение для text_file - txt, doc, pdf, fb2");
        //System.out.println("Введите, что бы переименовать: ");
        //System.out.println("---> rename_dir <имя_директории> <новое_имя_директории>");
    }

    void showMessage(String str) {
        System.out.println(str);
    }

    void showError(String str) {
        System.err.println(str);
    }

    void viewCatalog(Directory directory) {

        System.out.println(directory.getPath());
        for (File f : directory.getFiles()
        ) {
            System.out.println("  " + f.getName());
        }
        for (Directory d : directory.getSubDirectory()
        ) {
//            System.out.println("---");
//            System.out.println(d.getName());
//            System.out.println(d.getPath());
//            System.out.println("---");
            viewCatalog(d);
        }

    }
}
