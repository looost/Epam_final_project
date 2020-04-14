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

public class SearchGetCommand implements Command {

    private static final String ROUTING_PAGE = "search.jsp";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String query = req.getParameter("query");
//        if (query != null) {
//            try {
//                List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialBySearchForm(query);
//                List genres = ServiceFactory.getInstance().getGenreService().findAll();
//                req.setAttribute("shows", serialList);
//                req.setAttribute("genres", genres);
//
//            } catch (ServiceException e) {
//                e.printStackTrace();
//            }
//        }
//        String genreQuery = req.getParameter("genreId");
//        if (genreQuery != null) {
//            try {
//                List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialByGenre(genreQuery);
//                List genres = ServiceFactory.getInstance().getGenreService().findAll();
//                req.setAttribute("shows", serialList);
//                req.setAttribute("genres", genres);
//
//            } catch (ServiceException e) {
//                e.printStackTrace();
//            }
//        }


        try {
            SearchForm searchForm = new SearchForm(req.getParameter("query"), req.getParameterValues("genre"),
                    req.getParameterValues("country"), req.getParameterValues("studio"));

            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialBySearchForm(searchForm);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();

            req.setAttribute("shows", serialList);
            req.setAttribute("genres", genres);
            req.setAttribute("country", country);
            req.setAttribute("studio", studio);
            req.setAttribute("searchForm", searchForm);

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "К сожалению такой страницы не существует :(");
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
            //RoutingUtils.forwardToPage("error.jsp", req, resp);
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PAGE, req, resp);
        //RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
