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

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        String requestURI = req.getRequestURI()
//                .substring(req.getRequestURI().lastIndexOf('/') + 1, req.getRequestURI().lastIndexOf('.'));
        String requestURI2 = req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "-");
        try {
            CommandName commandName = CommandName.valueOf(requestURI2.toUpperCase());
            if (SecurityConfig.isSecurityPage(commandName)) {
                try {
                    String userLogin = (String) req.getSession().getAttribute("user");
                    User user = ServiceFactory.getInstance().getUserService().findByLogin(userLogin);
                    if (user == null) {
                        RoutingUtils.redirectToPage("/final/login.html", resp);
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
                        req.setAttribute("statusCode", HttpServletResponse.SC_FORBIDDEN);
                        RoutingUtils.forwardToPage("error.jsp", req, resp);
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
