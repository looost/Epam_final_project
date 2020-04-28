package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static by.training.utils.ConstantName.*;

public class IndexGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            if (req.getParameter(PARAMETER_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(PARAMETER_PAGE));
            }
            int countAllSerial = ServiceFactory.getInstance().getSerialService().countAllSerial();
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialPageByPage(page, COUNT_SERIAL_IN_MAIN_PAGE);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();
            List<Serial> last = ServiceFactory.getInstance().getSerialService().latestSerial(COUNT_LATEST_SHOWS);
            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countAllSerial);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_IN_MAIN_PAGE);
            req.setAttribute(PARAMETER_LAST_SHOWS, last);
            req.setAttribute(PARAMETER_COUNTRY, country);
            req.setAttribute(PARAMETER_STUDIO, studio);
            req.setAttribute(PARAMETER_GENRES, genres);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_INDEX_JSP, req, resp);
    }
}
