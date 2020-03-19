package by.training.controller.servlet;

import by.training.controller.command.CommandServletProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class XmlServlet extends HttpServlet {

    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        CommandServletProvider provider = new CommandServletProvider();
        provider.getCommand(requestURI).execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }
}
