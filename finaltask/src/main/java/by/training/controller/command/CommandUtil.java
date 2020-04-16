package by.training.controller.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        String att = (String) req.getSession().getAttribute(attName);
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
}
