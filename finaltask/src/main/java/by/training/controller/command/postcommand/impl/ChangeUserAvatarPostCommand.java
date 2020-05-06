package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static by.training.utils.ConstantName.*;

/**
 * Command for change {@link User} avatar.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class ChangeUserAvatarPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command for change {@link User} avatar. To work with downloading files
     * use {@link org.apache.commons.fileupload.FileUpload}. A folder for saving files declared in context.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = (String) req.getSession().getAttribute(ATTRIBUTE_USER);
            User user = ServiceFactory.getInstance().getUserService().findByLogin(login);
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            FileItem item = multiFiles.get(0);
            String fileName = UUID.randomUUID().toString() + item.getName();
            String filePath = req.getServletContext().getInitParameter(PATH_TO_UPLOAD_AVATAR_DIR)
                    + fileName;
            File avatar = new File(filePath);
            item.write(avatar);

            File copyFile = new File("D:\\Training\\finaltask\\src\\main\\webapp\\img\\avatar\\" + fileName);
            FileUtils.copyFile(avatar, copyFile);

            user.setAvatar(fileName);
            ServiceFactory.getInstance().getUserService().save(user);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_PROFILE_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        } catch (Exception e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
