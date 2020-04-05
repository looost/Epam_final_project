package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCountryCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryName = req.getParameter("country");
        Country country = new Country(countryName);
        try {
            ServiceFactory.getInstance().getCountryService().create(country);
            RoutingUtils.redirectToPage("/final/profile.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}