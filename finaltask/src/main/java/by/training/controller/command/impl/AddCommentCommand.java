package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCommentCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            User user = ServiceFactory.getInstance().getUserService().findByLogin(session.getAttribute("user").toString());
            //User user = DaoFactory.getInstance().getUserDao(ConnectionPool.getInstance().getConnection()).findByLogin(session.getAttribute("user").toString());
            Comment newComment = new Comment(user, serial, comment);
            ServiceFactory.getInstance().getCommentService().create(newComment);
            req.setAttribute("show", serial);
            RoutingUtils.redirectToPage("show.html?id=" + id, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
