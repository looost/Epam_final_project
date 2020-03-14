package by.training.controller.servletcommand.impl;

import by.training.controller.servletcommand.CommandServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParserGetCommandServlet implements CommandServlet {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
    }
}
