package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeGenrePostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/admin/genre.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("genre");
        String id = req.getParameter("id");
        Genre genre = new Genre(Integer.valueOf(id), name);
        try {
            ServiceFactory.getInstance().getGenreService().update(genre);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
            //RoutingUtils.redirectToPage("/final/admin/genre.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
