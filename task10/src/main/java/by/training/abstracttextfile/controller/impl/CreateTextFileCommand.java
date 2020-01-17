package by.training.abstracttextfile.controller.impl;

import by.training.abstracttextfile.controller.Command;
import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.service.factory.ServiceFactory;

public class CreateTextFileCommand implements Command {
    @Override
    public String execute(Directory directory, String request) {
        if (request.split(" ").length < 2) {
            return "Не введено имя текстового файла!";
        }
        try {
            ServiceFactory.getInstance()
                    .getFileService()
                    .createTextFile(directory, request.split(" ")[1]);
            return "OK";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
