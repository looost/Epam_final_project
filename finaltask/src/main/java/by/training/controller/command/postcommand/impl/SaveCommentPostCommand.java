package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class SaveCommentPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String serialId = req.getParameter(PARAMETER_ID);
        String commentId = req.getParameter(PARAMETER_COMMENT_ID) != null ? req.getParameter(PARAMETER_COMMENT_ID) : String.valueOf(0);
        String text = req.getParameter(PARAMETER_COMMENT);
        try {
            User user = ServiceFactory.getInstance().getUserService()
                    .findByLogin(session.getAttribute(ATTRIBUTE_USER).toString());
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(serialId);
            if (serial != null) {
                Comment comment = new Comment(Integer.parseInt(commentId), user, serial, text);
                ServiceFactory.getInstance().getCommentService().save(comment);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
            } else {
                return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (ServiceException e) {
            logger.error(e);
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_COMMENT_PROBLEM, e.getMessage());
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
            } else {
                return CommandUtil.routingErrorPage(req, resp, e.getCode());
            }
        } catch (NumberFormatException e) {
            logger.error("Cannot cast value to int - " + commentId, e);
            req.getSession().setAttribute(ATTRIBUTE_STATUS_CODE, HttpServletResponse.SC_BAD_REQUEST);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
            //return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
