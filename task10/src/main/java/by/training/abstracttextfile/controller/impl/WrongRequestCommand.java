package by.training.abstracttextfile.controller.impl;

import by.training.abstracttextfile.controller.Command;
import by.training.abstracttextfile.entity.Directory;

public class WrongRequestCommand implements Command {
    @Override
    public String execute(Directory directory, String request) {
        return "Не верная команда!";
    }
}
