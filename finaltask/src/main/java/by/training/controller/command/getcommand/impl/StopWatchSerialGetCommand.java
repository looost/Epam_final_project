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

import static by.training.utils.ConstantName.*;

public class StopWatchSerialGetCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serialId = req.getParameter(PARAMETER_ID);
        int userId = (Integer) req.getSession().getAttribute(ATTRIBUTE_USER_ID);

            try {
                ServiceFactory.getInstance().getSerialService().stopWatchSerial(String.valueOf(userId), serialId);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }
}
