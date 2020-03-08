package by.training.controller.servlet;


import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(login, password, 3);
        try {
            DaoFactory.getInstance().getUserDao(ConnectionPool.getInstance().getConnection()).create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
