package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.ATTRIBUTE_INVALID_LOGIN;
import static by.training.utils.ConstantName.ROUTING_ADMIN_USER_JSP;

/**
 * Command for the {@link by.training.model.User} edit page.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class EditUserGetCommand implements Command {

    /**
     * Command for the {@link by.training.model.User} edit page.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferMapAttribute(ATTRIBUTE_INVALID_LOGIN, req);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_USER_JSP, req, resp);
    }
}
