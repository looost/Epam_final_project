package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Studio;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8");
        try {
            List<Country> countryList = DaoFactory.getInstance().getCountryDao().findAll();
            List<Studio> studios = DaoFactory.getInstance().getStudioDao(ConnectionPool.getInstance().getConnection()).findAll();
            List<Genre> genres = DaoFactory.getInstance().getGenreDao(ConnectionPool.getInstance().getConnection()).findAll();
            req.setAttribute("genres", genres);
            req.setAttribute("studio", studios);
            req.setAttribute("country", countryList);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("profile.jsp", req, resp);
    }
}
