package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.PARAMETER_LANGUAGE;
import static by.training.utils.ConstantName.HEADER_REFERER;

/**
 * Command for the change locale of the application.
 *
 * @see Command
 * @see by.training.controller.command.getcommand.GetCommandProvider
 */
public class LanguageGetCommand implements Command {

    /**
     * Command for the change locale of the application.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(PARAMETER_LANGUAGE);
        Cookie languageCookie = new Cookie(PARAMETER_LANGUAGE, language);
        resp.addCookie(languageCookie);
        return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
    }
}
