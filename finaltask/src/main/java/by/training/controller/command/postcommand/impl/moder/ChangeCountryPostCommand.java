package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeCountryPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("country");
        String id = req.getParameter("id");
        Country country = new Country(Integer.parseInt(id), name);
        try {
            ServiceFactory.getInstance().getCountryService().update(country);
            RoutingUtils.redirectToPage("/final/admin/country.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
