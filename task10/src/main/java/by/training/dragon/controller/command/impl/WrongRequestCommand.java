package by.training.dragon.controller.command.impl;

import by.training.dragon.controller.command.Command;
import by.training.dragon.controller.command.CommandResponse;

import java.util.ArrayList;

public class WrongRequestCommand implements Command {
    @Override
    public CommandResponse execute(String request) {
        return new CommandResponse(new ArrayList<>(), "Неверная команда!");
    }
}
