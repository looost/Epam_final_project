package by.training.abstracttextfile.service.factory;

import by.training.abstracttextfile.service.DirectoryService;
import by.training.abstracttextfile.service.FileService;
import by.training.abstracttextfile.service.impl.DirectoryServiceImpl;
import by.training.abstracttextfile.service.impl.FileServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final DirectoryService directoryService = new DirectoryServiceImpl();
    private final FileService fileService = new FileServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public FileService getFileService() {
        return fileService;
    }
}
