package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class SaveGenrePostCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreName = req.getParameter(PARAMETER_GENRE);
        String id = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
        if (genreName.equals("")) {
            String errorMessage = ResourceManager.INSTANCE.changeResource(req).getString("fillOutField");
            req.getSession().setAttribute(ATTRIBUTE_GENRE_PROBLEM, errorMessage);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
        }
        Genre genre = new Genre(Integer.parseInt(id), genreName);
        try {
            ServiceFactory.getInstance().getGenreService().save(genre);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_GENRE_PROBLEM, e.getMessage());
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
            }
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
