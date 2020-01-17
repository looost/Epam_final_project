package by.training.abstracttextfile.controller.impl;

import by.training.abstracttextfile.controller.Command;
import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.service.factory.ServiceFactory;

public class CreateFileCommand implements Command {
    @Override
    public String execute(Directory directory, String request) {
        if (request.split(" ").length < 2) {
            return "Не введено имя файла!";
        }
        ServiceFactory.getInstance()
                .getFileService()
                .createFile(directory, request.split(" ")[1]);
        return "OK";
    }
}
