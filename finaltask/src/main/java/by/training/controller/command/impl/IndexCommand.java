package by.training.controller.command.impl;

import by.training.controller.command.Command;
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

public class IndexCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setCharacterEncoding("UTF-8");
        try {
//            int page = 1;
//            if (req.getParameter("page") != null) {
//                page = Integer.parseInt(req.getParameter("page"));
//            }
            //Serial serial = by.training.service.impl.test.ServiceFactory.getInstance().getSerialService().findById("1");
            //System.out.println(serial);
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List<Serial> last = serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
            req.setAttribute("shows", serialList);
            req.setAttribute("last", last);
            req.setAttribute("genres", genres);
            req.setAttribute("country", country);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        RoutingUtils.forwardToPage("index.jsp", req, resp);
    }
}
