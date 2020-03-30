package by.training.controller.servlet;


import by.training.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;

    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1, req.getRequestURI().lastIndexOf("."));
        CommandProvider.getInstance().getCommand(requestURI).execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }
}
