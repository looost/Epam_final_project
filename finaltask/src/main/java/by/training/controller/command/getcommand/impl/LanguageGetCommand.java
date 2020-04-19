package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class LanguageGetCommand implements Command {

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(PARAMETER_LANGUAGE);
        Cookie languageCookie = new Cookie(PARAMETER_LANGUAGE, language);
        resp.addCookie(languageCookie);
        return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
    }
}
