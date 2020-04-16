package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordPostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/profile.html";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = (String) req.getSession().getAttribute("user");
            String password = BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt());
            String newPassword = BCrypt.hashpw(req.getParameter("newPassword"), BCrypt.gensalt());
            User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                user = new User(login, newPassword);
                ServiceFactory.getInstance().getUserService().update(user);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
            } else {
                String errorMessage = ResourceManager.INSTANCE
                        .changeResource(req)
                        .getString("invalidPassword");
                req.getSession().setAttribute("invalidPassword", errorMessage);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
            }
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }
    }
}
