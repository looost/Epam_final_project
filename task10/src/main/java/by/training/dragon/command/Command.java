package by.training.dragon.command;

import by.training.dragon.entity.Treasure;

import java.util.List;

public interface Command {
    public List<Treasure> execute(String request);
}
