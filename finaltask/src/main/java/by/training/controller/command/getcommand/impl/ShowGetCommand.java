package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
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

import static by.training.utils.ConstantName.*;

public class ShowGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger("debug");

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_ID);
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
            List<Serial> last = serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();


            if (req.getSession().getAttribute(ATTRIBUTE_USER_ID) != null) {
                boolean watchStatus = ServiceFactory.getInstance()
                        .getSerialService()
                        .userWatchThisSerial((String) req.getSession().getAttribute(ATTRIBUTE_USER_ID), id);
                req.setAttribute(ATTRIBUTE_WATCH_STATUS, watchStatus);
            }

            req.setAttribute(ATTRIBUTE_GENRES, genres);
            req.setAttribute(ATTRIBUTE_SHOW, serial);
            req.setAttribute("last", last);
            req.setAttribute(ATTRIBUTE_COUNTRY, country);
            req.setAttribute(ATTRIBUTE_STUDIO, studio);

        } catch (ServiceException e) {
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_SHOW_JSP, req, resp);
    }
}
