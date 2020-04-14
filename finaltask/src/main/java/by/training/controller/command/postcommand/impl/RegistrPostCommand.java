package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrPostCommand implements Command {

    private static final String ROUTING_PAGE = "/final/registration.html";
    private static final String ROUTING_LOGIN_PAGE = "/final/login.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password, 3);

        try {
            if (ServiceFactory.getInstance().getUserService().create(user)) {
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_LOGIN_PAGE, req, resp);
                //RoutingUtils.redirectToPage("/final/login.html", resp);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("incorrect", "Пользователь с таким логином уже существует!");
                req.getSession().setAttribute("error", error);
                //req.getSession().setAttribute("incorrect", "Пользователь с таким логином уже существует!");
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
                //RoutingUtils.redirectToPage("/final/registration.html", resp);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
