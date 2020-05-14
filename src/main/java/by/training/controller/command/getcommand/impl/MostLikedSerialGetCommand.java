package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
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
 * Command for the most liked serial.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class MostLikedSerialGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for the most liked serial.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAllSerial = ServiceFactory.getInstance().getSerialService().countAllSerial();
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findMostLikedSerial(page, COUNT_SERIAL_RATING_PAGE);
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List<Country> country = ServiceFactory.getInstance().getCountryService().findAll();
            List<Studio> studio = ServiceFactory.getInstance().getStudioService().findAll();
            List<Serial> last = ServiceFactory.getInstance().getSerialService().latestSerial(COUNT_LATEST_SHOWS);
            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countAllSerial);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_RATING_PAGE);
            req.setAttribute(PARAMETER_LAST_SHOWS, last);
            req.setAttribute(PARAMETER_GENRES, genres);
            req.setAttribute(PARAMETER_COUNTRY, country);
            req.setAttribute(PARAMETER_STUDIO, studio);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_RATING_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
