package by.training.utils;

import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.ATTRIBUTE_STATUS_CODE;
import static by.training.utils.ConstantName.ROUTING_ERROR_JSP;

/**
 * The class is intended for routing.
 */
public final class RoutingUtils {

    private RoutingUtils() {
    }

    /**
     * Forward to page.
     *
     * @param jspPage the jsp page
     * @param req     the HttpServletRequest
     * @param resp    the HttpServletResponse
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "page/" + jspPage);
        req.getRequestDispatcher("/WEB-INF/jsp/page-template.jsp").forward(req, resp);
    }

    /**
     * Redirect to page.
     *
     * @param jspPage the jsp page
     * @param resp    the resp\
     * @throws IOException      the io exception
     */
    public static void redirectToPage(String jspPage, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(jspPage);
    }

    /**
     * The method determines how to go to the page and redirects.
     *
     * @param cr the {@link CommandResponse}
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public static void routing(CommandResponse cr) throws ServletException, IOException {
        if (cr.getRoutingType().equals(RoutingType.FORWARD)) {
            forwardToPage(cr.getRoutingPage(), cr.getRequest(), cr.getResponse());
        } else {
            redirectToPage(cr.getRoutingPage(), cr.getResponse());
        }
    }

    /**
     * The method returns an {@link CommandResponse} redirected to the error page.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @param code {@link by.training.service.exception.ServiceException} code
     * @return the {@link CommandResponse}
     */
    public static CommandResponse routingErrorPage(HttpServletRequest req, HttpServletResponse resp, int code) {
        req.setAttribute(ATTRIBUTE_STATUS_CODE, code);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }

}
