package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.controller.filter.RoleEnum;
import by.training.model.Comment;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class DeleteCommentPostCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_ID);
        HttpSession session = req.getSession();
        try {
            User user = ServiceFactory.getInstance().getUserService()
                    .findByLogin(session.getAttribute(ATTRIBUTE_USER).toString());
            Comment comment = ServiceFactory.getInstance().getCommentService().findById(id);

            if (user != null && comment != null && (comment.getUser().getId() == user.getId() || user.getRole() == RoleEnum.MODER.ordinal())) {
                ServiceFactory.getInstance().getCommentService().delete(id);
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (ServiceException e) {
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
