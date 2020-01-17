package by.training.abstracttextfile.controller.impl;

import by.training.abstracttextfile.controller.Command;
import by.training.abstracttextfile.entity.Directory;
import by.training.abstracttextfile.service.factory.ServiceFactory;

public class RenameDirectoryImpl implements Command {
    @Override
    public String execute(Directory directory, String request) {
//        System.out.println(ServiceFactory.getInstance()
//                .getDirectoryService()
//                .getDirectory(directory, request.split(" ")[1]).getName());

        ServiceFactory.getInstance()
                .getDirectoryService()
                .getDirectory(directory, request.split(" ")[1])
                .setName(request.split(" ")[2]);

//        System.out.println(ServiceFactory.getInstance()
//                .getDirectoryService()
//                .getDirectory(directory, request.split(" ")[2]).getName());

        return "OK";
    }
}
