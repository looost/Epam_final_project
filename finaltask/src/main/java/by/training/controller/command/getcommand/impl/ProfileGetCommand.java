package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.ATTRIBUTE_INVALID_PASSWORD;
import static by.training.utils.ConstantName.ROUTING_PROFILE_JSP;

public class ProfileGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_INVALID_PASSWORD, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PROFILE_JSP, req, resp);
    }
}
