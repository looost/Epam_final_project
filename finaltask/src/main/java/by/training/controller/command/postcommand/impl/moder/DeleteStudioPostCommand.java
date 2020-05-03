package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class DeleteStudioPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_ID);
        try {
            ServiceFactory.getInstance().getStudioService().delete(id);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_STUDIO_PAGE, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}
