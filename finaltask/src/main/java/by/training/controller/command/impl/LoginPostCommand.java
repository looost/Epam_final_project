package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = ServiceFactory.getInstance()
                    .getUserService().findByLoginAndPassword(login, password);
//            user = DaoFactory.getInstance()
//                    .getUserDao(ConnectionPool.getInstance().getConnection()).findByLoginAndPassword(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getLogin());
            session.setAttribute("userId", user.getId());
            RoutingUtils.redirectToPage("/final/index.html", resp);
        } else {
            RoutingUtils.redirectToPage("/final/login.html", resp);
        }
    }
}
