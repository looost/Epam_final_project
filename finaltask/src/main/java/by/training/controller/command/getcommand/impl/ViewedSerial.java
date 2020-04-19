package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class ViewedSerial implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getSession().getAttribute(ATTRIBUTE_USER_ID).toString();
        if (userId != null) {
            try {
                List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialsThatIWatch(userId);
                req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_MY_SHOW_JSP, req, resp);
        //RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
