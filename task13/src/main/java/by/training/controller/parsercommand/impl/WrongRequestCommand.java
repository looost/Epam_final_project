package by.training.controller.parsercommand.impl;

import by.training.controller.parsercommand.Command;
import by.training.controller.parsercommand.CommandResponse;

import java.util.HashSet;

public class WrongRequestCommand implements Command {

    private static final String WRONG_REQUEST = "Wrong request";

    @Override
    public CommandResponse getSerials(String filePath) {
        return new CommandResponse(WRONG_REQUEST, new HashSet<>());
    }
}
