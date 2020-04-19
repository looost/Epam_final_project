package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class SearchGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            SearchForm searchForm = new SearchForm(req.getParameter(PARAMETER_QUERY), req.getParameterValues(PARAMETER_GENRE),
                    req.getParameterValues(PARAMETER_COUNTRY), req.getParameterValues(PARAMETER_STUDIO));

            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialBySearchForm(searchForm);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();

            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(ATTRIBUTE_GENRES, genres);
            req.setAttribute(ATTRIBUTE_COUNTRY, country);
            req.setAttribute(ATTRIBUTE_STUDIO, studio);
            req.setAttribute(ATTRIBUTE_SEARCH_FORM, searchForm);

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            req.setAttribute(ATTRIBUTE_ERROR, "К сожалению такой страницы не существует :(");
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
            //RoutingUtils.forwardToPage("error.jsp", req, resp);
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_SEARCH_JSP, req, resp);
        //RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
