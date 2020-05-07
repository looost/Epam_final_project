package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.controller.validation.Validation;
import by.training.controller.validation.impl.CountryValidationImpl;
import by.training.model.Country;
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
 * Command to save {@link by.training.model.Country}.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class SaveCountryPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    /**
     * Data validator for {@link Country}.
     */
    private static final Validation<Country> VALIDATOR = new CountryValidationImpl();

    /**
     * Command to save {@link by.training.model.Country}. Handles both change and creation requests.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryName = req.getParameter(PARAMETER_COUNTRY);
        String id = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
        Country country = new Country(Integer.parseInt(id), countryName);
        try {
            Map<String, String> errors = new HashMap<>();
            if (!VALIDATOR.isValid(country, errors)) {
                req.getSession().setAttribute(ATTRIBUTE_COUNTRY_PROBLEM, errors);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
            }
            ServiceFactory.getInstance().getCountryService().save(country);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}
