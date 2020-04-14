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

public class ChangeCountryPostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/admin/country.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("country");
        String id = req.getParameter("id");
        Country country = new Country(Integer.parseInt(id), name);
        try {
            ServiceFactory.getInstance().getCountryService().update(country);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
            //RoutingUtils.redirectToPage("/final/admin/country.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
