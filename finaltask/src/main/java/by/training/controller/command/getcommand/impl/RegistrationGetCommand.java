package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static by.training.utils.ConstantName.ATTRIBUTE_ERROR;
import static by.training.utils.ConstantName.ROUTING_REGISTRATION_JSP;

public class RegistrationGetCommand implements Command {

    private void init(HttpServletRequest req) {
        Map<String, String> error = (Map<String, String>) req.getSession().getAttribute(ATTRIBUTE_ERROR);
        if (error != null) {
            Iterator<Map.Entry<String, String>> iterator1 = error.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<String, String> entry = iterator1.next();
                req.setAttribute(entry.getKey(), entry.getValue());
                iterator1.remove();
            }
        }
    }

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String str = (String) req.getSession().getAttribute("incorrect");
//        if (str != null) {
//            req.getSession().removeAttribute("incorrect");
//            req.setAttribute("incorrect2", str);
//        }
        init(req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_REGISTRATION_JSP, req, resp);
        //RoutingUtils.forwardToPage("registration.jsp", req, resp);
    }
}
