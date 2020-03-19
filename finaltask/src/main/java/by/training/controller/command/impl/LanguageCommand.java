package by.training.controller.command.impl;

import by.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");

        Cookie languageCookie = new Cookie("language", language);
        resp.addCookie(languageCookie);

        resp.sendRedirect(req.getHeader("referer"));
    }
}
