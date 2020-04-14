package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCommentPostCommand implements Command {

    private static final String ROUTING_PAGE = "show.html?id=";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE + id, req, resp);
            //RoutingUtils.redirectToPage("show.html?id=" + id, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
