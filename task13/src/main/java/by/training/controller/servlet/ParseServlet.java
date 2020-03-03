package by.training.controller.servlet;

import by.training.entity.Genre;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialStAXBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@WebServlet("/xml/parser")
public class ParseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("parser");


        String path = "E:\\Java-Training\\task13\\src\\main\\resources\\xml\\serials.xml";
        BaseBuilder builder1 = new SerialStAXBuilder();
        builder1.buildSetSerials(path);
        Set<Serial> serials = builder1.getSerials();

        req.setAttribute("parser", name);
        req.setAttribute("serials", serials);

        req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
    }
}
