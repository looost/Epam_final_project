package by.training.abstracttextfile.service;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.entity.File;
import by.training.abstracttextfile.entity.TextFile;

public interface FileService {
    File createFile(Directory directory, String name);

    TextFile createTextFile(Directory directory, String name);
}
