package by.training.controller.command.impl.moder;

import by.training.controller.command.Command;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCountryCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            ServiceFactory.getInstance().getCountryService().delete(id);
            RoutingUtils.redirectToPage("/final/profile.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
