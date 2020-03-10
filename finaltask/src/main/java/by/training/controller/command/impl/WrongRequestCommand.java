package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongRequestCommand implements Command {
    @Override
    public void executeDoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("error.jsp", req, resp);
    }

    @Override
    public void executeDoPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // unsupported
    }
}
