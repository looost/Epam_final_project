package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
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
 * Command for the {@link by.training.model.User} edit page.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class EditUserGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    /**
     * Command for the {@link by.training.model.User} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferMapAttribute(ATTRIBUTE_INVALID_LOGIN, req);
        String login = req.getParameter(PARAMETER_LOGIN);
        if (login != null) {
            try {
                User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
                req.setAttribute(ATTRIBUTE_USER, user);
            } catch (ServiceException e) {
                logger.error(e);
                return RoutingUtils.routingErrorPage(req, resp, e.getCode());
            }
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_USER_JSP, req, resp);
    }
}
