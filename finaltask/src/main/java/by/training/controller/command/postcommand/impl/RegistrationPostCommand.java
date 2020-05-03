package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.RoleEnum;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class RegistrationPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = BCrypt.hashpw(req.getParameter(PARAMETER_PASSWORD), BCrypt.gensalt());
        User user = new User(login, password, DEFAULT_AVATAR_NAME, RoleEnum.USER.ordinal());
        try {
            ServiceFactory.getInstance().getUserService().save(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_LOGIN_PAGE, req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_INVALID_LOGIN,
                        "Пользователь с таким логином уже существует!");
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_REGISTRATION_PAGE, req, resp);
            } else {
                logger.error(e);
                return CommandUtil.routingErrorPage(req, resp, e.getCode());
            }
        }
    }
}
