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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginPostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/index.html";
    private static final String ROUTING_LOGIN_PAGE = "/final/login.html";
    private static final String ROUTING_ERROR_PAGE = "error.jsp";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = ServiceFactory.getInstance()
                    .getUserService().findByLogin(login);
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getLogin());
            session.setAttribute("userId", String.valueOf(user.getId()));
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        } else {
            String errorMessage = ResourceManager.INSTANCE
                    .changeResource(req)
                    .getString("incorrectLoginOrPassword");
            req.getSession().setAttribute("incorrectLoginOrPassword", errorMessage);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_LOGIN_PAGE, req, resp);
        }
    }
}
