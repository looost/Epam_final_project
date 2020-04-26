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
import java.util.List;

import static by.training.utils.ConstantName.*;

public class ShowGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger("debug");

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_COMMENT_PROBLEM, req);
        String id = req.getParameter(PARAMETER_ID);
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            List<Serial> last = ServiceFactory.getInstance().getSerialService().latestSerial(COUNT_LATEST_SHOWS);
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();

            if (req.getSession().getAttribute(ATTRIBUTE_USER_ID) != null) {
                boolean watchStatus = ServiceFactory.getInstance()
                        .getSerialService()
                        .userWatchThisSerial(String.valueOf(req.getSession().getAttribute(ATTRIBUTE_USER_ID)), id);
                req.setAttribute(ATTRIBUTE_WATCH_STATUS, watchStatus);
                boolean likedStatus = ServiceFactory.getInstance()
                        .getSerialService()
                        .userLikedThisSerial(String.valueOf(req.getSession().getAttribute(ATTRIBUTE_USER_ID)), id);
                req.setAttribute(ATTRIBUTE_LIKED_STATUS, likedStatus);
            }

            req.setAttribute(ATTRIBUTE_GENRES, genres);
            req.setAttribute(ATTRIBUTE_SHOW, serial);
            req.setAttribute(PARAMETER_LAST_SHOWS, last);
            req.setAttribute(ATTRIBUTE_COUNTRY, country);
            req.setAttribute(ATTRIBUTE_STUDIO, studio);

            return new CommandResponse(RoutingType.FORWARD, ROUTING_SHOW_JSP, req, resp);
        } catch (ServiceException e) {
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }

    }
}
