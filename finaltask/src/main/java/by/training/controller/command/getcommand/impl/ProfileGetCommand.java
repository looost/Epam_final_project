package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class ProfileGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_INVALID_PASSWORD, req);
        try {
            String login = (String) req.getSession().getAttribute(ATTRIBUTE_USER);
            User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
            req.setAttribute(ATTRIBUTE_USER_AVATAR, user.getAvatar());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_PROFILE_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
