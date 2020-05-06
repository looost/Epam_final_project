package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.utils.ConstantName;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

/**
 * The command to add a {@link User} to an application
 * with rights {@link by.training.model.RoleEnum} MODER OR ADMIN.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class AddUserPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * The command to add a {@link User} to an application
     * with rights {@link by.training.model.RoleEnum} MODER OR ADMIN.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = BCrypt.hashpw(req.getParameter(ConstantName.PARAMETER_PASSWORD), BCrypt.gensalt());
        String role = req.getParameter(PARAMETER_ROLE);
        if (login.equals("")) {
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
        }
        try {
            User user = new User(login, password, DEFAULT_AVATAR_NAME, Integer.parseInt(role));
            ServiceFactory.getInstance().getUserService().save(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
