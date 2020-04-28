package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static by.training.utils.ConstantName.*;

public class RegistrationGetCommand implements Command {



    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_INVALID_LOGIN, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_REGISTRATION_JSP, req, resp);
    }
}
