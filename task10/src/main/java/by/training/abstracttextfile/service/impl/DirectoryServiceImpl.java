package by.training.abstracttextfile.service.impl;

import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.service.DirectoryService;

public class DirectoryServiceImpl implements DirectoryService {
    @Override
    public Directory createDirectory(Directory main, String name) {
        Directory subDirectory = new Directory(name);
        main.addSubDirectory(subDirectory);
        return subDirectory;
    }

    @Override
    public Directory getDirectory(Directory main, String name) {
        return main.getDirectory(name);
    }
}
