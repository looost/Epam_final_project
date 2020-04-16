package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Genre;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserPostCommand implements Command {
    private static final String ROUTING_PAGE = "/final/admin/user.html";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt());
        String role = req.getParameter("role");
        if (login.equals("")) {
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        }
        try {
            User user = new User(login, password, Integer.parseInt(role));
            ServiceFactory.getInstance().getUserService().createUserWithRole(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }
    }
}
