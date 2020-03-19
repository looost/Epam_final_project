package by.training.controller.servlet;


import by.training.controller.servlet.handler.MultiFilesHandler;
import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {
            List<Country> countryList = DaoFactory.getInstance().getCountryDao(ConnectionPool.getInstance().getConnection()).findAll();
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        String name = req.getParameter("name");
//        String description = req.getParameter("description");
//        String logo = req.getParameter("logo");
//        String fullLogo = req.getParameter("full_logo");
//        String date = req.getParameter("release_date");
//        String countryId = req.getParameter("country_id");
//        String studioId = req.getParameter("studio_id");
//
//        Serial serial = new Serial();
//        serial.setName(name);
//        serial.setDescription(description);
//        serial.setLogo(logo);
//        serial.setFullLogo(fullLogo);
//        serial.setReleaseDate(Date.valueOf(date));
//        Country country = new Country();
//        country.setId(Integer.valueOf(countryId));
//        serial.setCountry(country);
//        Studio studio = new Studio();
//        studio.setId(Integer.valueOf(studioId));
//        serial.setStudio(studio);
//
//        try {
//            ServiceFactory.getInstance().getSerialService().create(serial);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            for (FileItem i : multiFiles
            ) {
                System.out.println(i.getFieldName());
            }


            Serial serial = MultiFilesHandler.Handler(multiFiles);
            System.out.println(serial);
            ServiceFactory.getInstance().getSerialService().create(serial);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        String[] arr = req.getParameterValues("genre");
//        System.out.println(req.getParameter("release_date"));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(req.getParameter("country"));
//        System.out.println(req.getParameter("studio"));
        resp.sendRedirect("profile");
    }
}
