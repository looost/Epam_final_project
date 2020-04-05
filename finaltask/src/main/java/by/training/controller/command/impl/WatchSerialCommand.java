package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.service.validation.Validation;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WatchSerialCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serialId = req.getParameter("id");
        String userId = req.getSession().getAttribute("userId").toString();
        if (userId != null) {
            try {
                ServiceFactory.getInstance().getSerialService().toWatchSerial(userId, serialId);
                RoutingUtils.redirectToPage("/final/show.html?id=" + serialId, resp);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
