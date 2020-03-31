package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;
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
            user = DaoFactory.getInstance()
                    .getUserDao(ConnectionPool.getInstance().getConnection()).findByLoginAndPassword(login, password);
        } catch (DaoException e) {
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
