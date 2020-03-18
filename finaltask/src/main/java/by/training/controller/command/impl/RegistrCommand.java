package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password, 3);

        try {
            if (ServiceFactory.getInstance().getUserService().create(user)) {
                RoutingUtils.redirectToPage("/final/login", resp);
            } else {
                req.getSession().setAttribute("incorrect", "Пользователь с таким логином уже существует!");
                RoutingUtils.redirectToPage("/final/registration", resp);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
