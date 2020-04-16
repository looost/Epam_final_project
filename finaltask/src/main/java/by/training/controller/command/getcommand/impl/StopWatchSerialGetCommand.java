package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StopWatchSerialGetCommand implements Command {

    private static final String ROUTING_PAGE = "/final/show.html?id=";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serialId = req.getParameter("id");
        String userId = (String) req.getSession().getAttribute("userId");
        if (userId != null) {
            try {
                ServiceFactory.getInstance().getSerialService().stopWatchSerial(userId, serialId);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE + serialId, req, resp);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
    }
}
