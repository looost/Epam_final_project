package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialStAXBuilder;
import by.training.service.exception.ServiceException;
import by.training.service.validation.ValidationXML;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StAXParserCommand implements Command {
    @Override
    public CommandResponse getSerials(String filePath) {
        CommandResponse response;
        if (!ValidationXML.xmlResolutionIsValid(filePath)) {
            response = new CommandResponse("file resolution should be xml", new HashSet<>());
            return response;
        }
        try {
            BaseBuilder baseBuilder = new SerialStAXBuilder();
            baseBuilder.buildSetSerials(filePath);
            response = new CommandResponse("OK", baseBuilder.getSerials());
            return response;
        } catch (ServiceException e) {
            Set<Serial> empty = new HashSet<>();
            response = new CommandResponse(e.getMessage(), empty);
            return response;
        }
    }
}
