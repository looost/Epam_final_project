package by.training.controller.parsercommand.impl;

import by.training.controller.parsercommand.Command;
import by.training.controller.parsercommand.CommandResponse;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialDomBuilder;
import by.training.service.exception.ServiceException;
import by.training.service.validation.ValidationXML;

import java.util.HashSet;
import java.util.Set;

public class DOMParserCommand implements Command {

    @Override
    public CommandResponse getSerials(String filePath) {
        CommandResponse response;
        if (!ValidationXML.xmlResolutionIsValid(filePath)) {
            response = new CommandResponse("file resolution should be xml", new HashSet<>());
            return response;
        }
        try {
            BaseBuilder baseBuilder = new SerialDomBuilder();
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
