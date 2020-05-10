package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.controller.validation.Validation;
import by.training.controller.validation.impl.RegistrationValidationImpl;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ConstantName;
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
     * Data validator for {@link User}.
     */
    private static final Validation<User> VALIDATOR = new RegistrationValidationImpl();

    /**
     * The command to add a {@link User} to an application
     * with rights {@link by.training.model.RoleEnum} MODER OR ADMIN.
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
        String role = req.getParameter(PARAMETER_ROLE);
        User user = new User(login, password, DEFAULT_AVATAR_NAME, Integer.parseInt(role));
        try {
            ServiceFactory.getInstance().getUserService().save(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == ServiceException.BAD_REQUEST && e.getErrors() != null) {
                req.getSession().setAttribute(ATTRIBUTE_INVALID_LOGIN, e.getErrors());
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
            }
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
