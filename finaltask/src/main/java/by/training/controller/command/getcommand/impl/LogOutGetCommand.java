package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class LogOutGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(ATTRIBUTE_USER);
        session.removeAttribute(ATTRIBUTE_USER_ROLE);
        session.removeAttribute(ATTRIBUTE_USER_ID);
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
    }
}
