package by.training.controller.servletcommand.impl;

import by.training.controller.servletcommand.CommandServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommandServlet implements CommandServlet {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        req.getSession().setAttribute("language", language);

//        Cookie languageCookie = new Cookie("language", language);
//        resp.addCookie(languageCookie);

        resp.sendRedirect(req.getHeader("referer"));
    }
}
