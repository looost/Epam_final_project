package by.training.controller.filter;

import by.training.controller.command.CommandName;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURI2 = req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "_");
        try {
            CommandName commandName = CommandName.valueOf(requestURI2.toUpperCase());
            if (SecurityConfig.isSecurityPage(commandName)) {
                try {
                    String userLogin = (String) req.getSession().getAttribute(ATTRIBUTE_USER);
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
                } catch (ServiceException e) {
                }
            }
        } catch (IllegalArgumentException e) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
