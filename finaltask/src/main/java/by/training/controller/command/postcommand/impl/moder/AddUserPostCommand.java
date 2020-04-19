package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.utils.ConstantName;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class AddUserPostCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = BCrypt.hashpw(req.getParameter(ConstantName.PARAMETER_PASSWORD), BCrypt.gensalt());
        String role = req.getParameter(PARAMETER_ROLE);
        if (login.equals("")) {
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
        }
        try {
            User user = new User(login, password, Integer.parseInt(role));
            ServiceFactory.getInstance().getUserService().createUserWithRole(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_USER_PAGE, req, resp);
        } catch (ServiceException e) {
            req.setAttribute(ATTRIBUTE_ERROR, e.getMessage());
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
        }
    }
}
