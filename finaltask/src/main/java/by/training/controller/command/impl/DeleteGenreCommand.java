package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteGenreCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            ServiceFactory.getInstance().getGenreService().delete(id);
            RoutingUtils.redirectToPage("/final/profile.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
