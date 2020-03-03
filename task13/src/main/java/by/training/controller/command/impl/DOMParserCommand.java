package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialDomBuilder;
import by.training.service.factory.ServiceFactory;

import java.util.Set;

public class DOMParserCommand implements Command {

    @Override
    public Set<Serial> getSerials(String request) {
        BaseBuilder baseBuilder = new SerialDomBuilder();
        baseBuilder.buildSetSerials(request);
        return baseBuilder.getSerials();
    }
}
