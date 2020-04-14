package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudioPostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/admin/studio.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studioName = req.getParameter("studio");
        Studio studio = new Studio(studioName);
        try {
            ServiceFactory.getInstance().getStudioService().create(studio);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
            //RoutingUtils.redirectToPage("/final/admin/studio.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
