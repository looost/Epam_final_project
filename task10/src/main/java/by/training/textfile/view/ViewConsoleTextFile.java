package by.training.textfile.view;

import by.training.textfile.entity.TextFile;

public class ViewConsoleTextFile {
    public void showData(TextFile file) {
        file.getData().forEach(System.out::println);
    }

    public void showName(TextFile file) {
        System.out.println(file.getName() + "." + file.getExpansion());
    }
}
