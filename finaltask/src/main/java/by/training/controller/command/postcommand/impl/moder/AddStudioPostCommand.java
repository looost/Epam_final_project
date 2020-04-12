package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;

import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudioPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studioName = req.getParameter("studio");
        Studio studio = new Studio(studioName);
        try {
            ServiceFactory.getInstance().getStudioService().create(studio);
            RoutingUtils.redirectToPage("/final/admin/studio.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
