package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
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

public class EditSerialGetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandUtil.transferSingleAttribute(ATTRIBUTE_SERIAL_PROBLEM, req);
        try {
            List<Country> countryList = ServiceFactory.getInstance().getCountryService().findAll();
            List<Studio> studios = ServiceFactory.getInstance().getStudioService().findAll();
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            req.setAttribute(PARAMETER_GENRES, genres);
            req.setAttribute(PARAMETER_STUDIO, studios);
            req.setAttribute(PARAMETER_COUNTRY, countryList);
            return new CommandResponse(RoutingType.FORWARD, ROUTING_ADMIN_SERIAL_JSP, req, resp);
        } catch (ServiceException e) {
            logger.error(e);
            return CommandUtil.routingErrorPage(req, resp, e.getCode());
        }
    }
}

