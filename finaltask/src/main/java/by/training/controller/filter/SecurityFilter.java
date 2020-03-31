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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI()
                .substring(req.getRequestURI().lastIndexOf('/') + 1, req.getRequestURI().lastIndexOf('.'));
        CommandName commandName = CommandName.valueOf(requestURI.toUpperCase());

        if (commandName.equals(CommandName.LOGIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (SecurityUtils.isSecurityPage(commandName)) {
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
                if (SecurityUtils.hasPermission(commandName, userRole)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                } else {
                    req.setAttribute("error", "НЕТ ДОСТУПА");
                    RoutingUtils.forwardToPage("error.jsp", req, resp);
                    return;
                }
            } catch (ServiceException e) {
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
