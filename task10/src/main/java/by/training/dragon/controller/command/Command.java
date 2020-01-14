package by.training.dragon.controller.command;

import by.training.dragon.entity.Treasure;

import java.util.List;

public interface Command {
    List<Treasure> execute(String request);
}
