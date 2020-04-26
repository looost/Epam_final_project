package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class ChangePasswordPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = (String) req.getSession().getAttribute(ATTRIBUTE_USER);
            String password = req.getParameter(PARAMETER_PASSWORD);
            String newPassword = BCrypt.hashpw(req.getParameter(PARAMETER_NEW_PASSWORD), BCrypt.gensalt());
            User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                user = new User(login, newPassword);
                ServiceFactory.getInstance().getUserService().update(user);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PROFILE_PAGE, req, resp);
            } else {
                String errorMessage = ResourceManager.INSTANCE
                        .changeResource(req)
                        .getString(ATTRIBUTE_INVALID_PASSWORD);
                req.getSession().setAttribute(ATTRIBUTE_INVALID_PASSWORD, errorMessage);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PROFILE_PAGE, req, resp);
            }
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
