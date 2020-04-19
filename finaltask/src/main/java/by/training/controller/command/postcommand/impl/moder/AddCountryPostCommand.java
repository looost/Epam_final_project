package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.*;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.PARAMETER_COUNTRY;
import static by.training.utils.ConstantName.ROUTING_COUNTRY_PAGE;

public class AddCountryPostCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryName = req.getParameter(PARAMETER_COUNTRY);
        if (countryName == null) {
            req.getSession().setAttribute("countryProblem", "Введите название страны");
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
        }
        Country country = new Country(countryName);
        try {
            ServiceFactory.getInstance().getCountryService().create(country);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
            //RoutingUtils.redirectToPage("/final/admin/country.html", resp);
        } catch (ServiceException e) {
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
