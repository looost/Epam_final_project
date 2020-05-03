package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class EditStudioGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_STUDIO_PROBLEM, req);
        try {
            int page = req.getParameter(PARAMETER_PAGE) != null ? Integer.parseInt(req.getParameter(PARAMETER_PAGE)) : DEFAULT_PAGE_NUMBER;
            int countAllStudio = ServiceFactory.getInstance().getStudioService().countAllStudio();
            List<Studio> studios = ServiceFactory.getInstance()
                    .getStudioService().findStudioPageByPage(page, COUNT_STUDIO_IN_ADMIN_PAGE);
            req.setAttribute(PARAMETER_STUDIO, studios);
            req.setAttribute(PARAMETER_COUNT_ALL_STUDIOS, countAllStudio);
            req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_STUDIO_IN_ADMIN_PAGE);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_STUDIO_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}

