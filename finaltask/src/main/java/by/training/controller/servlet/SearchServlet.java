package by.training.controller.servlet;

import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        try {
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialBySearchForm(query);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            req.setAttribute("shows", serialList);
            req.setAttribute("genres", genres);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
