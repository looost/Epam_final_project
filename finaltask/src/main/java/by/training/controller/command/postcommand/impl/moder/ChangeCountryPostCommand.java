package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class ChangeCountryPostCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(PARAMETER_COUNTRY);
        String id = req.getParameter(PARAMETER_ID);
        Country country = new Country(Integer.parseInt(id), name);
        try {
            ServiceFactory.getInstance().getCountryService().update(country);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
            //RoutingUtils.redirectToPage("/final/admin/country.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
