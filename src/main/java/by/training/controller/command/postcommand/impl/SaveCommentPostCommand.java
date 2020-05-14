package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

/**
 * Command to save {@link Comment}.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class SaveCommentPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to save {@link Comment}. Handles both change and creation requests.
     *
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String serialId = req.getParameter(PARAMETER_ID);
        String commentId = req.getParameter(PARAMETER_COMMENT_ID) != null ? req.getParameter(PARAMETER_COMMENT_ID) : String.valueOf(0);
        String text = req.getParameter(PARAMETER_COMMENT);
        try {
            User user = ServiceFactory.getInstance().getUserService()
                    .findByLogin(session.getAttribute(ATTRIBUTE_LOGIN).toString());
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(serialId);
            if (serial != null) {
                Comment comment = new Comment(Integer.parseInt(commentId), user, serial, text);
                ServiceFactory.getInstance().getCommentService().save(comment);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
            } else {
                return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (ServiceException e) {
            if (e.getCode() == ServiceException.BAD_REQUEST && e.getErrors() != null) {
                req.getSession().setAttribute(ATTRIBUTE_COMMENT_PROBLEM, e.getErrors());
                return new CommandResponse(RoutingType.REDIRECT,
                        ROUTING_SHOW_PAGE + "?id=" + serialId, req, resp);
            }
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (NumberFormatException e) {
            logger.error("Cannot cast value to int - " + commentId, e);
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
