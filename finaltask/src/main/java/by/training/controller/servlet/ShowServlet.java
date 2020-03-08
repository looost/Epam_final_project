package by.training.controller.servlet;

import by.training.dao.ConnectionPool;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;


@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            req.setAttribute("show", serial);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("show.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String comment = req.getParameter("comment");
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            User user = DaoFactory.getInstance().getUserDao(ConnectionPool.getInstance().getConnection()).findByLogin(session.getAttribute("user").toString());
            Comment newComment = new Comment(user, serial, comment);
            ServiceFactory.getInstance().getCommentService().create(newComment);
            req.setAttribute("show", serial);
            doGet(req, resp);
        } catch (DaoException | ServiceException e) {
            e.printStackTrace();
        }

    }
}
