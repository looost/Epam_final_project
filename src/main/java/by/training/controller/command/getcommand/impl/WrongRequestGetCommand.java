package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.ATTRIBUTE_STATUS_CODE;
import static by.training.utils.ConstantName.ROUTING_ERROR_JSP;

/**
 * Command for requests that the application cannot process.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class WrongRequestGetCommand implements Command {

    /**
     * Command for requests that the application cannot process.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_STATUS_CODE, HttpServletResponse.SC_NOT_FOUND);
        return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_JSP, req, resp);
    }
}
