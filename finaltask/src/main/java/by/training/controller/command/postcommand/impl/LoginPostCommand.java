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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class LoginPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        User user;
        try {
            user = ServiceFactory.getInstance()
                    .getUserService().findByLogin(login);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute(ATTRIBUTE_USER, user.getLogin());
            session.setAttribute(ATTRIBUTE_USER_ROLE, user.getRole());
            session.setAttribute(ATTRIBUTE_USER_ID, user.getId());
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
        } else {
            String errorMessage = ResourceManager.INSTANCE
                    .changeResource(req)
                    .getString("incorrectLoginOrPassword");
            req.getSession().setAttribute("incorrectLoginOrPassword", errorMessage);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_LOGIN_PAGE, req, resp);
        }
    }
}
