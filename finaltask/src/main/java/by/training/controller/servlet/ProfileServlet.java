package by.training.controller.servlet;


import by.training.model.Country;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RoutingUtils.forwardToPage("profile.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String logo = req.getParameter("logo");
        String fullLogo = req.getParameter("full_logo");
        String date = req.getParameter("release_date");
        String countryId = req.getParameter("country_id");
        String studioId = req.getParameter("studio_id");

        Serial serial = new Serial();
        serial.setName(name);
        serial.setDescription(description);
        serial.setLogo(logo);
        serial.setFullLogo(fullLogo);
        serial.setReleaseDate(Date.valueOf(date));
        Country country = new Country();
        country.setId(Integer.valueOf(countryId));
        serial.setCountry(country);
        Studio studio = new Studio();
        studio.setId(Integer.valueOf(studioId));
        serial.setStudio(studio);

        try {
            ServiceFactory.getInstance().getSerialService().create(serial);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(req, resp);
    }
}
