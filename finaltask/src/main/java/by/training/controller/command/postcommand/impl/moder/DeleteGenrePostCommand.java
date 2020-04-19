package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.PARAMETER_ID;
import static by.training.utils.ConstantName.ROUTING_GENRE_PAGE;

public class DeleteGenrePostCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_ID);
        try {
            ServiceFactory.getInstance().getGenreService().delete(id);
            //RoutingUtils.redirectToPage("/final/admin/genre.html", resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_GENRE_PAGE, req, resp);
    }
}
