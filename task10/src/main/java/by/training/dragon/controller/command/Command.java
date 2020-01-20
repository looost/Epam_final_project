package by.training.dragon.controller.command;

public interface Command {
    CommandResponse execute(String request);
}
