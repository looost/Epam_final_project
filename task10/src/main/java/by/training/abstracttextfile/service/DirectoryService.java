package by.training.abstracttextfile.service;

import by.training.abstracttextfile.entity.Directory;

public interface DirectoryService {
    Directory createDirectory(Directory main, String name);

    Directory getDirectory(Directory main, String name);
}
