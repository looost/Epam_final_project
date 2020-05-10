package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
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
import java.util.List;

import static by.training.utils.ConstantName.*;

/**
 * Command for the {@link by.training.model.Serial} edit page.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class EditSerialGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the {@link by.training.model.Serial} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferMapAttribute(ATTRIBUTE_SERIAL_PROBLEM, req);
        try {
            List<Country> countryList = ServiceFactory.getInstance().getCountryService().findAll();
            List<Studio> studios = ServiceFactory.getInstance().getStudioService().findAll();
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            req.setAttribute(PARAMETER_GENRES, genres);
            req.setAttribute(PARAMETER_STUDIO, studios);
            req.setAttribute(PARAMETER_COUNTRY, countryList);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_SERIAL_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }
    }
}

