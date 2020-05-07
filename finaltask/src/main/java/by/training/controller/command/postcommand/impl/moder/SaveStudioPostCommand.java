package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.controller.validation.Validation;
import by.training.controller.validation.impl.StudioValidationImpl;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.training.utils.ConstantName.*;

/**
 * Command to save {@link by.training.model.Studio}.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class SaveStudioPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Data validator for {@link Studio}.
     */
    private static final Validation<Studio> VALIDATOR = new StudioValidationImpl();

    /**
     * Command to save {@link by.training.model.Studio}. Handles both change and creation requests.
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
        String studioName = req.getParameter(PARAMETER_STUDIO);
        String id = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
        Studio studio = new Studio(Integer.parseInt(id), studioName);
        try {
            Map<String, String> errors = new HashMap<>();
            if (!VALIDATOR.isValid(studio, errors)) {
                req.getSession().setAttribute(ATTRIBUTE_STUDIO_PROBLEM, errors);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_STUDIO_PAGE, req, resp);
            }
            ServiceFactory.getInstance().getStudioService().save(studio);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_STUDIO_PAGE, req, resp);
        } catch (ServiceException e) {
                logger.error(e);
                return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}

