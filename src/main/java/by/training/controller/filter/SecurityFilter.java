package by.training.controller.filter;

import by.training.controller.command.CommandName;
import by.training.model.RoleEnum;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

/**
 * The filter checks if the {@link User} with {@link RoleEnum} has the necessary authentication and authorization.
 * Implementation of {@link Filter}.
 */
public class SecurityFilter implements Filter {

    /**
     * Checks if the {@link User} with {@link RoleEnum} has the necessary authentication and authorization.
     *
     * @param servletRequest  the <code>ServletRequest</code> object contains the client's request
     * @param servletResponse the <code>ServletResponse</code> object contains the filter's response
     * @param filterChain     the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see UnavailableException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURI2 = req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "_");
        try {
            CommandName commandName = CommandName.valueOf(requestURI2.toUpperCase());
            if (SecurityConfig.isSecurityPage(commandName)) {
                String userLogin = (String) req.getSession().getAttribute(ATTRIBUTE_LOGIN);
                User user = ServiceFactory.getInstance().getUserService().findByLogin(userLogin);
                if (user == null) {
                    RoutingUtils.redirectToPage(ROUTING_LOGIN_PAGE, resp);
                    return;
                }
                RoleEnum userRole = null;
                for (RoleEnum r : RoleEnum.values()
                ) {
                    if (r.ordinal() == user.getRole()) {
                        userRole = r;
                        break;
                    }
                }
                if (SecurityConfig.hasPermission(commandName, userRole)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                } else {
                    req.setAttribute(ATTRIBUTE_STATUS_CODE, HttpServletResponse.SC_FORBIDDEN);
                    RoutingUtils.forwardToPage(ROUTING_ERROR_JSP, req, resp);
                    return;
                }
            }
        } catch (IllegalArgumentException | ServiceException e) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
