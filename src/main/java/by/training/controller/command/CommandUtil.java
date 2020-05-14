package by.training.controller.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Utility class for working with {@link HttpServletRequest}.
 */
public final class CommandUtil {

    private CommandUtil() {
    }

    /**
     * Transfer single attribute from session attribute to request attribute.
     *
     * @param attName the attribute name that is in session
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
     * Transfer the value from the map to the request attribute.
     *
     * @param mapName name of the map that is in session
     * @param req     the {@link HttpServletRequest}
     */
    public static void transferMapAttribute(final String mapName, final HttpServletRequest req) {
        Object o = req.getSession().getAttribute(mapName);
        if (o instanceof Map) {
            Map<?, ?> errors = (Map<?, ?>) o;
            for (Map.Entry<?, ?> entry : errors.entrySet()
            ) {
                req.setAttribute((String) entry.getKey(), entry.getValue());
            }
            req.getSession().removeAttribute(mapName);
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
