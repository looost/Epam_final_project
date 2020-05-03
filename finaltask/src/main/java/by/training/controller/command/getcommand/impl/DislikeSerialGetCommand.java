package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class DislikeSerialGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serialId = req.getParameter(PARAMETER_ID);
        int userId = (Integer) req.getSession().getAttribute(ATTRIBUTE_USER_ID);
        try {
            ServiceFactory.getInstance().getSerialService().dislikeSerial(String.valueOf(userId), serialId);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
