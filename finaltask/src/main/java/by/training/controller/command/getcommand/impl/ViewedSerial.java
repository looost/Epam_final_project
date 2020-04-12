package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewedSerial implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        RoutingUtils.forwardToPage("search.jsp", req, resp);
    }
}
