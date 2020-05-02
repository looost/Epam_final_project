package by.training.controller.command.postcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static by.training.utils.ConstantName.*;

public class ChangeUserAvatarPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        } catch (Exception e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
