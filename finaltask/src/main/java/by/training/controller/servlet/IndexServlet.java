package by.training.controller.servlet;


import by.training.controller.command.CommandResponse;
import by.training.controller.command.getcommand.GetCommandProvider;
import by.training.controller.command.postcommand.PostCommandProvider;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;

//    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        System.out.println(req.getServletPath());
//        //String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1, req.getRequestURI().lastIndexOf("."));
//        String requestURI2 = req.getServletPath().substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "-");
//        System.out.println(requestURI2);
//        GetCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
//    }

    private static String getRequestURI(HttpServletRequest req) {
        return req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "_");

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = GetCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = PostCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }
}
