package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = (String) req.getSession().getAttribute("incorrect");
        if (str != null) {
            req.getSession().removeAttribute("incorrect");
            req.setAttribute("incorrect2", str);
        }
        RoutingUtils.forwardToPage("registration.jsp", req, resp);
    }
}
