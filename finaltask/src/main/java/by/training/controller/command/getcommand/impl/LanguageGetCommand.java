package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageGetCommand implements Command {

    private static final String ROUTING_PAGE = "referer";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");

        Cookie languageCookie = new Cookie("language", language);
        resp.addCookie(languageCookie);


        return new CommandResponse(RoutingType.REDIRECT, req.getHeader(ROUTING_PAGE), req, resp);
        //resp.sendRedirect(req.getHeader("referer"));
    }
}
