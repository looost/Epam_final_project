package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewedSerial implements Command {

    private static final String ROUTING_PAGE = "search.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        if (userId != null) {
            try {
                List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findSerialsThatIWatch(userId);
                List genres = ServiceFactory.getInstance().getGenreService().findAll();
                List country = ServiceFactory.getInstance().getCountryService().findAll();
                List studios = ServiceFactory.getInstance().getStudioService().findAll();
                req.setAttribute("shows", serialList);
                req.setAttribute("genres", genres);
                req.setAttribute("country", country);
                req.setAttribute("studio", studios);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_PAGE, req, resp);
        //RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
