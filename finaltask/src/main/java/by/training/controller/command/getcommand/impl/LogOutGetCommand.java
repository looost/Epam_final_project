package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutGetCommand implements Command {

    private static final String ROUTING_PAGE = "/final/index.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        //RoutingUtils.redirectToPage("/final/index.html", resp);
    }
}
