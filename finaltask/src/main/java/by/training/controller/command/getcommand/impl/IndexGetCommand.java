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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static by.training.utils.ConstantName.*;

public class IndexGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            if (req.getParameter(PARAMETER_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(PARAMETER_PAGE));
            }
            int countAllSerial = ServiceFactory.getInstance().getSerialService().countAllSerial();
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAllSerial2(page, COUNT_SERIAL_IN_MAIN_PAGE);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();
            List<Serial> last = serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countAllSerial);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_IN_MAIN_PAGE);
            req.setAttribute("last", last);
            req.setAttribute(PARAMETER_GENRES, genres);
            req.setAttribute(PARAMETER_COUNTRY, country);
            req.setAttribute(PARAMETER_STUDIO, studio);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_INDEX_JSP, req, resp);
    }
}
