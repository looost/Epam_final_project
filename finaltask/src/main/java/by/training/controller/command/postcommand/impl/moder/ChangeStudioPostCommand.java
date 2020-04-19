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

import static by.training.utils.ConstantName.*;

public class ChangeStudioPostCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(PARAMETER_STUDIO);
        String id = req.getParameter(PARAMETER_ID);
        Studio studio = new Studio(Integer.parseInt(id), name);
        try {
            ServiceFactory.getInstance().getStudioService().update(studio);
            //RoutingUtils.redirectToPage("/final/admin/studio.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_STUDIO_PAGE, req, resp);
    }
}
