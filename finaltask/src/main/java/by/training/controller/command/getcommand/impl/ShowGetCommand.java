package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowGetCommand implements Command {

    private static final String ROUTING_PAGE = "show.jsp";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";
    private static final Logger logger = LogManager.getLogger("debug");

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
            List<Serial> last = serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();


            if (req.getSession().getAttribute("userId") != null) {
                boolean watchStatus = ServiceFactory.getInstance()
                        .getSerialService()
                        .userWatchThisSerial((String) req.getSession().getAttribute("userId"), id);
                req.setAttribute("watchStatus", watchStatus);
            }

            req.setAttribute("genres", genres);
            req.setAttribute("show", serial);
            req.setAttribute("last", last);
            req.setAttribute("country", country);
            req.setAttribute("studio", studio);

        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PAGE, req, resp);
    }
}
