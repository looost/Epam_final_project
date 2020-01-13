package by.training.dragon.command.impl;

import by.training.dragon.command.Command;
import by.training.dragon.entity.Treasure;

import java.util.ArrayList;
import java.util.List;

public class WrongRequestCommand implements Command {
    @Override
    public List<Treasure> execute(String request) {
        return new ArrayList<>();
    }
}
