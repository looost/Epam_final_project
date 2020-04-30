package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.model.form.SearchForm;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class SearchGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;


            SearchForm searchForm = new SearchForm(req.getParameter(PARAMETER_QUERY), req.getParameterValues(PARAMETER_GENRE),
                    req.getParameterValues(PARAMETER_COUNTRY), req.getParameterValues(PARAMETER_STUDIO));

            int countAllSerial = ServiceFactory.getInstance().getSerialService().countAllSerialsBySearchForm(searchForm);
            List<Serial> serialList = ServiceFactory.getInstance()
                    .getSerialService().findSerialBySearchForm(searchForm, page, COUNT_SERIAL_IN_SEARCH_PAGE);
            List genres = ServiceFactory.getInstance().getGenreService().findAll();
            List country = ServiceFactory.getInstance().getCountryService().findAll();
            List studio = ServiceFactory.getInstance().getStudioService().findAll();
            req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countAllSerial);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_IN_SEARCH_PAGE);
            req.setAttribute(ATTRIBUTE_SHOWS, serialList);
            req.setAttribute(ATTRIBUTE_GENRES, genres);
            req.setAttribute(ATTRIBUTE_COUNTRY, country);
            req.setAttribute(ATTRIBUTE_STUDIO, studio);
            req.setAttribute(ATTRIBUTE_SEARCH_FORM, searchForm);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_SEARCH_JSP, req, resp);
        } catch (ServiceException e) {
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
