package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class EditCountryGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_COUNTRY_PROBLEM, req);
        try {
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAllCountry = ServiceFactory.getInstance().getCountryService().countAllCountry();
            List<Country> countries = ServiceFactory.getInstance()
                    .getCountryService().findCountryPageByPage(page, COUNT_COUNTY_IN_ADMIN_PAGE);
            req.setAttribute(PARAMETER_COUNTRY, countries);
            req.setAttribute(PARAMETER_COUNT_ALL_COUNTRY, countAllCountry);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_COUNTY_IN_ADMIN_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_COUNTRY_JSP, req, resp);
        //RoutingUtils.forwardToPage("/admin/country.jsp", req, resp);
    }
}

