package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongRequestGetCommand implements Command {

    private static final String ROUTING_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "К сожалению такой страницы не существует :(");
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PAGE, req, resp);
        //RoutingUtils.forwardToPage("error.jsp", req, resp);
    }
}
