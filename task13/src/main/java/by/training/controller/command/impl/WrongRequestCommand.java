package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.entity.Serial;

import java.util.HashSet;
import java.util.Set;

public class WrongRequestCommand implements Command {
    @Override
    public Set<Serial> getSerials(String request) {
        return new HashSet<>();
    }
}
