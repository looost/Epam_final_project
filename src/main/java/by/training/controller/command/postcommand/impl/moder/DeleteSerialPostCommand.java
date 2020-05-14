package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static by.training.utils.ConstantName.*;

/**
 * Command to delete {@link by.training.model.Serial}.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class DeleteSerialPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to delete {@link by.training.model.Serial}. Pictures are also deleted.
     * A folder for files declared in context.
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
        String id = req.getParameter(PARAMETER_ID);
        try {
            Serial serial = ServiceFactory.getInstance().getSerialService().findById(id);
            ServiceFactory.getInstance().getSerialService().delete(id);
            File logo = new File(req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                    + serial.getLogo());
            File fullLogo = new File(req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                    + serial.getFullLogo());
            if (logo.exists() && !logo.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(logo.toPath());
            }
            if (fullLogo.exists() && !fullLogo.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(fullLogo.toPath());
            }

            File logoCopy = new File("D:\\Training\\finaltask\\src\\main\\webapp\\img\\"
                    + serial.getLogo());
            File fullCopy = new File("D:\\Training\\finaltask\\src\\main\\webapp\\img\\"
                    + serial.getFullLogo());
            if (logoCopy.exists() && !logoCopy.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(logoCopy.toPath());
            }
            if (fullCopy.exists() && !fullCopy.getName().equals(DEFAULT_IMG_NAME)) {
                Files.delete(fullCopy.toPath());
            }

            return new CommandResponse(RoutingType.REDIRECT, ROUTING_INDEX_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return RoutingUtils.routingErrorPage(req, resp, e.getCode());
        }

    }
}
