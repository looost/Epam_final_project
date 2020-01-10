package by.training.textfile.service;

import by.training.textfile.FileException;
import by.training.textfile.entity.Directory;
import by.training.textfile.entity.TextFile;

public class TextFileService {

    public TextFile createByDefault(Directory directory) {
        try {
            return new TextFile("default", "txt", directory);
        } catch (FileException e) {
            return null;             // недостижимый Exception
        }
    }

    public void addData(TextFile file, String data) {
        file.getData().add(data);
    }

    public void addData(TextFile originalFile, TextFile addedFile) {
        originalFile.getData().addAll(addedFile.getData());
    }

    public void rename(TextFile file, String newName) {
        file.setName(newName);
    }

    public void deleteString(TextFile file, String string) {
        file.getData().remove(string);
    }


}
