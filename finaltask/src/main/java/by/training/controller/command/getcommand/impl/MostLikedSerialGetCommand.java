package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;
import static by.training.utils.ConstantName.ROUTING_INDEX_JSP;

public class MostLikedSerialGetCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int page = 1;
            if (req.getParameter(PARAMETER_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(PARAMETER_PAGE));
            }
            int countAllSerial = ServiceFactory.getInstance().getSerialService().countAllSerial();
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findMostLikedSerial(page, COUNT_SERIAL_RATING_PAGE);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();
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
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }
    }
}