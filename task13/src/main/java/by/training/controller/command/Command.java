package by.training.controller.command;

import by.training.entity.Serial;

import java.util.Set;

public interface Command {
    Set<Serial> getSerials(String request);
}
