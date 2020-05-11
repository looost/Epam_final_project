package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

/**
 * Command for change {@link User} password.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class ChangePasswordPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    /**
     * Command for change {@link User} password.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = (String) req.getSession().getAttribute(ATTRIBUTE_USER);
            String password = req.getParameter(PARAMETER_PASSWORD);
            String newPassword = req.getParameter(PARAMETER_NEW_PASSWORD);
            User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
            if (user == null || !ServiceFactory.getInstance().getSecurityService().checkpw(password, user.getPassword())) {
                req.getSession().setAttribute(ATTRIBUTE_INVALID_PASSWORD, "invalidPassword");
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PROFILE_PAGE, req, resp);
            }
            user.setPassword(newPassword);
            ServiceFactory.getInstance().getUserService().save(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PROFILE_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
