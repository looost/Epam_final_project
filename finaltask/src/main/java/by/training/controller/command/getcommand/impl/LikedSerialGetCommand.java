package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
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
 * Command for liked serial.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class LikedSerialGetCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for liked serial.
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
            String userId = req.getSession().getAttribute(ATTRIBUTE_USER_ID).toString();
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countSerial = ServiceFactory.getInstance().getSerialService().countAllSerialsThatUserLiked(userId);
            List<Serial> serialList = ServiceFactory.getInstance()
                    .getSerialService().findSerialsThatUserLiked(userId, page, COUNT_SERIAL_IN_LIKED_PAGE);
            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_IN_LIKED_PAGE);
            req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countSerial);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_LIKED_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
