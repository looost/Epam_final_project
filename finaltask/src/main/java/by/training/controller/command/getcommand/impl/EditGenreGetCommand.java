package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class EditGenreGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CommandUtil.transferSingleAttribute(ATTRIBUTE_GENRE_PROBLEM, req);
            int page = DEFAULT_PAGE_NUMBER;
            if (req.getParameter(PARAMETER_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(PARAMETER_PAGE));
            }
            int countAllGenre = ServiceFactory.getInstance().getGenreService().countAllGenres();
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findGenrePageByPage(page, COUNT_GENRE_IN_ADMIN_PAGE);
            req.setAttribute(PARAMETER_GENRES, genres);
            req.setAttribute(PARAMETER_COUNT_ALL_GENRES, countAllGenre);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_GENRE_IN_ADMIN_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_GENRE_JSP, req, resp);
        //RoutingUtils.forwardToPage("/admin/genre.jsp", req, resp);
    }
}
