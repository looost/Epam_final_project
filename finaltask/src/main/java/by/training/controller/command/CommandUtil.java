package by.training.controller.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static by.training.utils.ConstantName.*;

public class CommandUtil {

    private CommandUtil() {
    }

    public static void transferMapAttribute(HttpServletRequest req) {

        Map<String, String> error = (Map<String, String>) req.getSession().getAttribute("error");
        if (error != null) {
            for (Map.Entry<String, String> entry : error.entrySet()) {
                req.setAttribute(entry.getKey(), entry.getValue());
            }
            req.getSession().removeAttribute("error");
        }
    }

    public static void transferSingleAttribute(String attName, HttpServletRequest req) {
        Object att = req.getSession().getAttribute(attName);
        if (att != null) {
            req.setAttribute(attName, att);
            req.getSession().removeAttribute(attName);
        }
    }

    public static Cookie searchCookie(String cookieName, HttpServletRequest req) {
        for (Cookie c : req.getCookies()
        ) {
            if (c.getName().equals(cookieName)) {
                return c;
            }
        }
        return null;
    }

    public static CommandResponse routingErrorPage(HttpServletRequest req, HttpServletResponse resp, int code) {
        req.setAttribute(ATTRIBUTE_STATUS_CODE, code);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }
}
