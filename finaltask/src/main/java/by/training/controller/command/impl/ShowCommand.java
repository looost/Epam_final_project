package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
            List<Serial> last = serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();


            req.setAttribute("genres", genres);
            req.setAttribute("show", serial);
            req.setAttribute("last", last);
            req.setAttribute("country", country);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("show.jsp", req, resp);
    }
}
