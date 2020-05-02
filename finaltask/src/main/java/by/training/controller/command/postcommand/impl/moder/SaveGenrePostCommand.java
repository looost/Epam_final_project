package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class SaveGenrePostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreName = req.getParameter(PARAMETER_GENRE);
        String id = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
        Genre genre = new Genre(Integer.parseInt(id), genreName);
        try {
            ServiceFactory.getInstance().getGenreService().save(genre);
            req.getSession().setAttribute("ok", "ok");
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_GENRE_PROBLEM, e.getMessage());
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
            } else {
                logger.error(e);
                return CommandUtil.routingErrorPage(req, resp, e.getCode());
            }
        }
    }
}
