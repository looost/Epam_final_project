package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialStAXBuilder;

import java.util.Set;

public class StAXParserCommand implements Command {
    @Override
    public Set<Serial> getSerials(String request) {
        BaseBuilder baseBuilder = new SerialStAXBuilder();
        baseBuilder.buildSetSerials(request);
        return baseBuilder.getSerials();
    }
}
