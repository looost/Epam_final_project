package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddGenrePostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/admin/genre.html";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreName = req.getParameter("genre");
        if (genreName.equals("")) {
            String errorMessage = ResourceManager.INSTANCE
                    .changeResource(req)
                    .getString("incorrectGenreName");
            req.getSession().setAttribute("genreProblem", errorMessage);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        }
        Genre genre = new Genre(genreName);
        try {
            ServiceFactory.getInstance().getGenreService().create(genre);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }
    }
}
