package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.entity.Treasure;

import java.util.ArrayList;
import java.util.List;

public class WrongRequestCommand implements Command {
    private static final String response = "Не верная команда!";
    @Override
    public List<Treasure> execute(String request) {
        return new ArrayList<>();
    }

    @Override
    public String getResponse() {
        return response;
    }
}
