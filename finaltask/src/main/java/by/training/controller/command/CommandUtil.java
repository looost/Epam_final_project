package by.training.controller.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Utility class for working with {@link HttpServletRequest}.
 */
public final class CommandUtil {

    private CommandUtil() {
    }

    /**
     * Transfer single attribute from session attribute to request attribute.
     *
     * @param attName the attribute name
     * @param req     the {@link HttpServletRequest}
     */
    public static void transferSingleAttribute(final String attName, final HttpServletRequest req) {
        Object att = req.getSession().getAttribute(attName);
        if (att != null) {
            req.setAttribute(attName, att);
            req.getSession().removeAttribute(attName);
        }
    }

    /**
     * Search cookie from {@link HttpServletRequest} by cookie name.
     *
     * @param cookieName the cookie name
     * @param req        the {@link HttpServletRequest}
     * @return the {@link Cookie} or null if not found
     */
    public static Cookie searchCookie(final String cookieName, final HttpServletRequest req) {
        for (Cookie c : req.getCookies()
        ) {
            if (c.getName().equals(cookieName)) {
                return c;
            }
        }
        return null;
    }
}
