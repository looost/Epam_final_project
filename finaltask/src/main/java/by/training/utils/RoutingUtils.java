package by.training.utils;

import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoutingUtils {

    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "page/" + jspPage);
        req.getRequestDispatcher("/WEB-INF/jsp/page-template.jsp").forward(req, resp);
    }

    public static void redirectToPage(String jspPage, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(jspPage);
    }

    public static void routing(CommandResponse cr) throws ServletException, IOException {
        if (cr.getRoutingType().equals(RoutingType.FORWARD)) {
            forwardToPage(cr.getRoutingPage(), cr.getRequest(), cr.getResponse());
        } else {
            redirectToPage(cr.getRoutingPage(), cr.getResponse());
        }
    }

}
