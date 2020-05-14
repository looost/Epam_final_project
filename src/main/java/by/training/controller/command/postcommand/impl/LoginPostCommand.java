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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

/**
 * {@link User} authentication command.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class LoginPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * {@link User} authentication command. It also checks if there is
     * a {@link User} in the system with such a login and if there is, does his password hash match.
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
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        User user;
        try {
            user = ServiceFactory.getInstance()
                    .getUserService().findByLogin(login);
            if (user == null ||
                    !ServiceFactory.getInstance().getSecurityService().checkpw(password, user.getPassword())) {
                req.getSession().setAttribute(ATTRIBUTE_INVALID_LOGIN, "incorrectLoginOrPassword");
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_LOGIN_PAGE, req, resp);
            }
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
        HttpSession session = req.getSession();
        session.setAttribute(ATTRIBUTE_LOGIN, user.getLogin());
        session.setAttribute(ATTRIBUTE_USER_ROLE, user.getRole());
        session.setAttribute(ATTRIBUTE_USER_ID, user.getId());
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
    }
}
