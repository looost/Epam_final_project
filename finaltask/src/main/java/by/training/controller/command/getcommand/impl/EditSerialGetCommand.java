package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditSerialGetCommand implements Command {

    private static final String ROUTING_PAGE = "/admin/serial.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("descriptionProblem") != null) {
            req.setAttribute("descriptionProblem", req.getSession().getAttribute("descriptionProblem"));
            req.getSession().removeAttribute("descriptionProblem");
        }
        try {
            List<Country> countryList = ServiceFactory.getInstance().getCountryService().findAll();
            List<Studio> studios = ServiceFactory.getInstance().getStudioService().findAll();
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            req.setAttribute("genres", genres);
            req.setAttribute("studio", studios);
            req.setAttribute("country", countryList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PAGE, req, resp);
        //RoutingUtils.forwardToPage("/admin/serial.jsp", req, resp);
    }
}

