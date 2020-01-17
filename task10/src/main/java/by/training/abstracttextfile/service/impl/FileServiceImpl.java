package by.training.abstracttextfile.service.impl;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;
import by.training.abstracttextfile.entity.TextFile;
import by.training.abstracttextfile.service.FileService;

public class FileServiceImpl implements FileService {
    @Override
    public File createFile(Directory directory, String name) {
        return new File(directory, name);
    }

    @Override
    public TextFile createTextFile(Directory directory, String name) {
        return new TextFile(directory, name);
    }
}
